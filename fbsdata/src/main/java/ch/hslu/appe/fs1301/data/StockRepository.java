package ch.hslu.appe.fs1301.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.iStockRepository;
import ch.hslu.appe.fs1301.data.shared.entity.ZentrallagerBestellung;

import com.google.inject.Inject;

public class StockRepository extends BaseRepository<ZentrallagerBestellung> implements iStockRepository {

	private final String DateFormat = "dd.MM.yyyy";
	private SimpleDateFormat fSimpleDateFormat;

	@Inject
	public StockRepository(iAPPEEntityManager entityManager) {
		super(entityManager);
		fSimpleDateFormat = new SimpleDateFormat(DateFormat);
	}

	@Override
	public void OrderFromStock(int productId, int count, Date deliveryDate) {
		StringBuilder query = new StringBuilder(100);
		query.append("Insert into ZentrallagerBestellung Values (0,");
		query.append(count).append(',');
		query.append("TO_DATE('").append(fSimpleDateFormat.format(deliveryDate)).append("', '").append(DateFormat).append("'),");
		query.append(productId).append(')');
		
		fEntityManager.executeInsertQuery(query.toString());
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
