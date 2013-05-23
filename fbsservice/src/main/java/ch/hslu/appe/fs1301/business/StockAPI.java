package ch.hslu.appe.fs1301.business;

import java.util.List;

import com.google.inject.Inject;

import ch.hslu.appe.fs1301.business.shared.iStockAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOConverter;
import ch.hslu.appe.fs1301.business.shared.dto.DTOZentrallagerBestellung;
import ch.hslu.appe.fs1301.data.shared.iStockRepository;
import ch.hslu.appe.fs1301.data.shared.iTransaction;

public class StockAPI extends BaseAPI implements iStockAPI {

	private iStockRepository fStockRepository;

	@Inject
	protected StockAPI(iStockRepository stockRepository, iTransaction transaction, iInternalSessionAPI sessionAPI) {
		super(transaction, sessionAPI);
		fStockRepository = stockRepository;
	}

	@Override
	public List<DTOZentrallagerBestellung> getOpenOrder() {
		return DTOConverter.convertZentrallagerBestellung(fStockRepository.getAll());
	}

	@Override
	public void ConfirmOrderReceivedFromStock(int id) {
		fTransaction.beginTransaction();
		fStockRepository.ConfirmOrderReceivedFromStock(id);
		fTransaction.commitTransaction();
	}

}
