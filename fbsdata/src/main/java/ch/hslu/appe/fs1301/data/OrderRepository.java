package ch.hslu.appe.fs1301.data;

import java.util.List;

import java.util.Date;

import com.google.inject.Inject;

import ch.hslu.appe.fs1301.data.shared.OrderFilter;
import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.iOrderRepository;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellung;
import ch.hslu.appe.fs1301.data.shared.entity.Person;

public class OrderRepository extends BaseRepository<Bestellung> implements iOrderRepository {

	@Inject
	public OrderRepository(iAPPEEntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Bestellung> getOrders() {
		StringBuilder query = new StringBuilder(100);
		
		query.append("SELECT b FROM Bestellung b");
		
		return executeQuery(query);
	}

	@Override
	public List<Bestellung> getOrders(OrderFilter filter) {
		StringBuilder query = new StringBuilder(100);
		
		query.append("SELECT b FROM Bestellung b WHERE ");
		query.append("b.liefertermin_Ist IS NULL");
		
		return executeQuery(query);
	}

	@Override
	public List<Bestellung> getOrders(Date startDate, Date endDate)
			throws IllegalArgumentException {
		
		if (startDate.after(endDate))
			throw new IllegalArgumentException("startdate must be befor enddate");
		
		StringBuilder query = new StringBuilder(100);
		query.append("SELECT b FROM Bestellung b WHERE ");
		query.append("b.bestelldatum IS NULL");
		query.append(startDate);
		query.append("' AND ");
		query.append("b.bestelldatum < '");
		query.append(endDate);
		query.append("'");
		
		return executeQuery(query);
	}

	@Override
	public List<Bestellung> getOrderByPerson(Person person) {

		StringBuilder query = new StringBuilder(100);
		query.append("SELECT b FROM Bestellung b WHERE ");
		query.append("b.person1 = ");
		query.append(person.getId());
		
		return executeQuery(query);
	}

	@Override
	public List<Bestellung> getOrdersByPersonIDs(int... ids)
			throws IllegalArgumentException {

		if (ids.length == 0)
			throw new IllegalArgumentException("ids");
		
		StringBuilder query = new StringBuilder(100);
		query.append("SELECT b FROM Bestellung b WHERE ");
		for(int id : ids) {
			query.append("(b.person1 = ");
			query.append(id);
			query.append(") OR ");
		}
		
		query.delete(query.length()-3, query.length());
		
		return executeQuery(query);
	}

}
