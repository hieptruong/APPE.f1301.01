package ch.hslu.appe.fs1301.business;

import java.util.Date;
import java.util.List;
import java.util.Map;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1301.business.shared.dto.DTOConverter;
import ch.hslu.appe.fs1301.business.shared.dto.DTOZentrallagerBestellung;
import ch.hslu.appe.fs1301.data.shared.iStockRepository;
import ch.hslu.appe.fs1301.data.shared.iTransaction;
import ch.hslu.appe.fs1301.data.shared.entity.Produkt;
import ch.hslu.appe.fs1301.data.shared.entity.ZentrallagerBestellung;
import ch.hslu.appe.stock.Stock;
import ch.hslu.appe.stock.StockException;
import ch.hslu.appe.stock.StockFactory;

import com.google.inject.Inject;
import com.sun.tools.javac.util.Pair;

public class StockAPI extends BaseAPI implements iInternalStockAPI {

	private iStockRepository fStockRepository;
	private Stock fStock;

	@Inject
	protected StockAPI(iStockRepository stockRepository, iTransaction transaction, iInternalSessionAPI sessionAPI) {
		super(transaction, sessionAPI);
		fStockRepository = stockRepository;
		fStock = StockFactory.getStock();	
	}

	@Override
	public List<DTOZentrallagerBestellung> getOpenOrders() throws AccessDeniedException {
		checkRole(UserRole.ADMIN);
		
		return DTOConverter.convertZentrallagerBestellung(fStockRepository.getAll());
	}

	@Override
	public void confirmOrderReceivedFromStock(int id) throws AccessDeniedException {
		checkRole(UserRole.ADMIN);
		
		fTransaction.beginTransaction();
		try {
			fStockRepository.confirmOrderReceivedFromStock(id);
		} catch (Exception exception) {
			fTransaction.rollbackTransaction();
			return;
		}
		fTransaction.commitTransaction();
	}

	@Override
	public Date finalizeOrder(Map<Long, Date> tickets) throws StockException {
		
		Date latestDate = new Date();
		
		for (Map.Entry<Long, Date> entry : tickets.entrySet()) {
			if (fStock.orderItem(entry.getKey()) == 0) {
				throw new StockException("Invalid Ticket");
			}
			latestDate = latestDate.before(entry.getValue()) ? entry.getValue() : latestDate;
		}
		
		return latestDate;
	}
	
	public Pair<Long, Date> reserveItem(Produkt produkt, int anzahl) throws StockException {
		String produktID = generateArticelIDforStock(produkt.getId());
		long ticket = fStock.reserveItem(produktID, anzahl);
		if (ticket == 0) throw new StockException("Not Enough Items in Stock");
		Date deliveryDate = fStock.getItemDeliveryDate(produktID);
		
		ZentrallagerBestellung stockOrder = new ZentrallagerBestellung();
		stockOrder.setProdukt(produkt);
		stockOrder.setAnzahl(anzahl);
		stockOrder.setLiefertermin(deliveryDate);
		
		fStockRepository.persistObject(stockOrder);
		
		return new Pair<Long, Date>(ticket, deliveryDate);
	}
	
	private String generateArticelIDforStock(int productID)
	{
		return String.format("%06d", productID);
	}

	@Override
	public void cancelReservedTickets(Map<Long, Date> tickets) {
		for (Map.Entry<Long, Date> entry : tickets.entrySet()) {
			try {
				fStock.freeItem(entry.getKey());
			} catch (StockException e) {
				e.printStackTrace();
			}			
		}
	}
}
