package ch.hslu.appe.fs1301.data;

import java.util.List;

import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.iOrderPositionRepository;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellposition;

import com.google.inject.Inject;

public class OrderPositionRepository extends BaseRepository<Bestellposition> implements iOrderPositionRepository {

	@Inject
	public OrderPositionRepository(iAPPEEntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public boolean orderProduct(int orderId, int productId, int count, int piecePrice) {
		StringBuilder query = new StringBuilder(100);
		query.append("Insert into Bestellposition Values (0,");
		query.append(count).append(',');
		query.append(piecePrice).append(',');
		query.append(productId).append(',');
		query.append(orderId).append(')');
		
		try {
			fEntityManager.executeInsertQuery(query.toString());
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	@Override
	public List<Bestellposition> getOrderPositionsById(int... ids) throws IllegalArgumentException {
		if (ids.length == 0)
			throw new IllegalArgumentException("names");
		
		StringBuilder query = new StringBuilder(100);
		query.append("SELECT p FROM Bestellposition p WHERE p.id in (");
		for(int id : ids) {
			query.append(id).append(',');
		}
		query.setCharAt(query.length() - 1, ')');
		
		return executeQuery(query);
	}

}
