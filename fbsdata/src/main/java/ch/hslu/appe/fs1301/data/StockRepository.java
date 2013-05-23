package ch.hslu.appe.fs1301.data;

import java.util.List;

import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.iStockRepository;
import ch.hslu.appe.fs1301.data.shared.entity.ZentrallagerBestellung;

import com.google.inject.Inject;

public class StockRepository extends BaseRepository<ZentrallagerBestellung> implements iStockRepository {

	@Inject
	public StockRepository(iAPPEEntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void ConfirmOrderReceivedFromStock(int id) {
		fEntityManager.executeProcedure("StockOrderReceived", id);
	}
	
	@Override
	public List<ZentrallagerBestellung> getAll() {
		final String Query = "SELECT z FROM ZentrallagerBestellung z";

		return executeQuery(Query);
	}
}
