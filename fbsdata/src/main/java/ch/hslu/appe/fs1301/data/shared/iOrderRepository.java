package ch.hslu.appe.fs1301.data.shared;

import java.util.Date;
import java.util.List;

import ch.hslu.appe.fs1301.data.shared.entity.Bestellung;
import ch.hslu.appe.fs1301.data.shared.entity.Person;

/**
 * @author Stefan Bachmann
 * iOrderRepository
 */
public interface iOrderRepository extends iRepository<Bestellung> {
	
	/**
	 * Gets all orders stored in the database
	 * 
	 * @return List of all orders
	 */
	public List<Bestellung> getOrders();
	
	/**
	 * Gets all orders stored in the database filtered by an OrderFilter
	 * 
	 * @return Filtered list of all orders
	 */
	public List<Bestellung> getOrders(OrderFilter filter);
	
	/**
	 * Gets all orders stored in the database filtered by startDate and endDate
	 * 
	 * @return Filtered list of all orders in this datetime range
	 */
	public List<Bestellung> getOrders(Date startDate, Date endDate) throws IllegalArgumentException;
	
	/**
	 * Get all orders of a person
	 * 
	 * @param person filter of the person
	 * @return List of all orders of a person
	 */
	public List<Bestellung> getOrderByPerson(Person person);
	
	
	/**
	 * Get all orders of the people
	 * 
	 * @param ids IDs of all people
	 * @return List of all orders
	 * @throws IllegalArgumentException
	 */
	public List<Bestellung> getOrdersByPersonIDs(int... ids) throws IllegalArgumentException;

}
