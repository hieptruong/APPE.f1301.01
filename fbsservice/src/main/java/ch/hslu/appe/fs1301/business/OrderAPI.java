package ch.hslu.appe.fs1301.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import ch.hslu.appe.fs1301.data.shared.entity.Produkt;
import ch.hslu.appe.stock.StockException;

import com.google.inject.Inject;
import com.sun.tools.javac.util.Pair;

public class OrderAPI extends BaseAPI implements iOrderAPI {

	private iPersonRepository fPersonRepository;
	private iOrderRepository fOrderRepository;
	private iOrderPositionRepository fPositionRepository;
	private iProductRepository fProductRepository;
	private iInternalStockAPI fInternalStockAPI;

	@Inject
	public OrderAPI(iTransaction transaction, iInternalSessionAPI sessionAPI, iOrderRepository orderRepository, 
					iOrderPositionRepository positionRepository, iPersonRepository personRepository, 
					iProductRepository productRepository, iInternalStockAPI internalStockAPI) {
		super(transaction, sessionAPI);
		fOrderRepository = orderRepository;
		fPositionRepository = positionRepository;
		fPersonRepository = personRepository;
		fProductRepository = productRepository;
		fInternalStockAPI = internalStockAPI;
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
			
			// The DeliveryDate could have been changed by orderProducts.
			fOrderRepository.persistObject(order);
			
			DTOBestellung dtoBestellung = new DTOBestellung(fOrderRepository.getById(order.getId()));
			fTransaction.commitTransaction();
			return dtoBestellung;
		} catch (Exception exception) {
			fTransaction.rollbackTransaction();
			return null;
		}
	}
	
	private boolean orderProducts(Bestellung order, List<DTOBestellposition> positions) {
		Map<Long, Date> tickets = new HashMap<Long, Date>();
		
		try
		{
			for(DTOBestellposition position : positions) {
				if (!fPositionRepository.orderProduct(order.getId(), position.getProdukt(), position.getAnzahl(), position.getStueckpreis(), true)) {
					//Not enough in storage
									
					Produkt produkt = fProductRepository.getById(position.getProdukt());
					Pair<Long,Date> reserveItem = fInternalStockAPI.reserveItem(produkt, position.getAnzahl() + produkt.getMinimalMenge());
					tickets.put(reserveItem.fst, reserveItem.snd);
					
					fPositionRepository.orderProduct(order.getId(), position.getProdukt(), position.getAnzahl(), position.getStueckpreis(), false);
				}
			}
			
			Date deliveryDate = fInternalStockAPI.finalizeOrder(tickets);
			order.setLiefertermin_Soll(deliveryDate);
			order.setLiefertermin_Ist(deliveryDate);
		}
		catch (StockException ex)
		{
			fInternalStockAPI.cancelReservedTickets(tickets);
			return false;
		}
		
		return true;
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
