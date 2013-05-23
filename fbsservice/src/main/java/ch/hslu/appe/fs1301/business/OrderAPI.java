package ch.hslu.appe.fs1301.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1301.business.shared.iOrderAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOConverter;
import ch.hslu.appe.fs1301.data.shared.iOrderPositionRepository;
import ch.hslu.appe.fs1301.data.shared.iOrderRepository;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.iProductRepository;
import ch.hslu.appe.fs1301.data.shared.iTransaction;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellposition;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellung;
import ch.hslu.appe.stock.Stock;
import ch.hslu.appe.stock.StockException;
import ch.hslu.appe.stock.StockFactory;

import com.google.inject.Inject;

public class OrderAPI extends BaseAPI implements iOrderAPI {

	private iPersonRepository fPersonRepository;
	private iOrderRepository fOrderRepository;
	private iOrderPositionRepository fPositionRepository;
	private iProductRepository fProductRepository;

	@Inject
	public OrderAPI(iTransaction transaction, iInternalSessionAPI sessionAPI, iOrderRepository orderRepository, 
					iOrderPositionRepository positionRepository, iPersonRepository personRepository, iProductRepository productRepository) {
		super(transaction, sessionAPI);
		fOrderRepository = orderRepository;
		fPositionRepository = positionRepository;
		fPersonRepository = personRepository;
		fProductRepository = productRepository;
	}

	@Override
	public DTOBestellung createNewOrder(int customerId, int source, List<DTOBestellposition> positions) throws AccessDeniedException {
		checkRole(UserRole.SYSUSER | UserRole.ADMIN);
				
		fTransaction.beginTransaction();
		try {
			//Save Order
			Bestellung order = new Bestellung();
			order.setQuelle(source);
			order.setPerson1(fPersonRepository.getById(customerId));
			order.setPerson2(fSessionAPI.getAuthenticatedUser());
			order.setBestelldatum(new Date());
			order.setLiefertermin_Ist(new Date());
			order.setLiefertermin_Soll(new Date());
			fOrderRepository.persistObject(order);
			
			//Save positions
			if (orderProducts(order, positions) == false) {
				fTransaction.rollbackTransaction();
				return null;
			}
					
			DTOBestellung dtoBestellung = new DTOBestellung(fOrderRepository.getById(order.getId()));
			fTransaction.commitTransaction();
			return dtoBestellung;
		} catch (Exception exception) {
			fTransaction.rollbackTransaction();
			return null;
		}
	}
	
	private boolean orderProducts(Bestellung order, List<DTOBestellposition> positions) {
		
		Map<Object, Object> reservedItems = new HashMap<Object, Object>();
		boolean canOrderAllProducts = true;
		//order from central stock
		Stock stock = StockFactory.getStock();	
		
		try
		{
			for(DTOBestellposition position : positions) {
				if (!fPositionRepository.orderProduct(order.getId(), position.getProdukt(), position.getAnzahl(), position.getStueckpreis())) {
					//Not enough in storage
									
					String artikelIDForStock = generateArticelIDforStock(position.getProdukt());
					
					int productMinimalCount = fProductRepository.getById(position.getProdukt()).getMinimalMenge();
					int productCountToOrder = position.getAnzahl();
	
					long ticket = stock.reserveItem(artikelIDForStock, productCountToOrder + productMinimalCount);
					
					if (ticket != 0)
					{
						reservedItems.put(artikelIDForStock, ticket);
					}
					else
					{
						canOrderAllProducts = false;
						break;
					}
				}
				
				if (canOrderAllProducts)
				{
					order.setLiefertermin_Soll(getLatestDeliveryDateFromStock(stock, reservedItems));
					orderTicketsFromStock(stock, reservedItems);
				}
				else
				{
					freeTicketsFromStock(stock, reservedItems);
			        return false;
				}
			}
		}
		catch (StockException ex)
		{
			return false;
		}
		
		return true;
	}
	
	private String generateArticelIDforStock(int productID)
	{
		StringBuilder artikelIDForStockBuilder = new StringBuilder(productID);
		while(artikelIDForStockBuilder.length() <= 6) {
			artikelIDForStockBuilder.append("0");
		}
		String artikelIDForStock = artikelIDForStockBuilder.toString();
		
		return artikelIDForStock;
	}
	
	private Date getLatestDeliveryDateFromStock(Stock stock, Map<Object, Object> reservedItems) throws StockException
	{
		Date latestDate = new Date();
		
		Set s= reservedItems.entrySet();
        Iterator it= s.iterator();

        while(it.hasNext())
        {
            Map.Entry m =(Map.Entry)it.next();

            String articelID = (String)m.getKey();
            long ticket = Long.valueOf((String) m.getValue());
            
            if (stock.getItemDeliveryDate(articelID).after(latestDate))
            {
            	latestDate = stock.getItemDeliveryDate(articelID);
            }
        }
 
        return latestDate;
	}
	
	private void orderTicketsFromStock(Stock stock, Map<Object, Object> reservedItems) throws StockException
	{
		Set s= reservedItems.entrySet();
        Iterator it= s.iterator();

        while(it.hasNext())
        {
            Map.Entry m =(Map.Entry)it.next();

            String articelID = (String)m.getKey();
            long ticket = Long.valueOf((String) m.getValue());
            
            stock.orderItem(ticket);
        }
	}
	
	private void freeTicketsFromStock(Stock stock, Map<Object, Object> reservedItems) throws StockException
	{
		Set s= reservedItems.entrySet();
        Iterator it= s.iterator();

        while(it.hasNext())
        {
            Map.Entry m =(Map.Entry)it.next();

            String articelID = (String)m.getKey();
            long ticket = Long.valueOf((String) m.getValue());
            
            stock.freeItem(ticket);
        }
	}

	@Override
	public List<DTOBestellung> getOrders(int... ids) throws AccessDeniedException {
		checkRole(UserRole.SYSUSER | UserRole.ADMIN);
		
		List<Bestellung> searchList;
		try {
			searchList = fOrderRepository.getOrdersById(ids);
		} catch(IllegalArgumentException exception) {
			searchList = new ArrayList<Bestellung>(0);
		}
		
		return DTOConverter.convertBestellung(searchList);
	}

	@Override
	public List<DTOBestellposition> getOrderPositions(int... ids) throws AccessDeniedException {
		checkRole(UserRole.SYSUSER | UserRole.ADMIN);
		
		List<Bestellposition> searchList;
		try {
			searchList = fPositionRepository.getOrderPositionsById(ids);
		} catch(IllegalArgumentException exception) {
			searchList = new ArrayList<Bestellposition>(0);
		}
		
		return DTOConverter.convertBestellposition(searchList);
	}
}
