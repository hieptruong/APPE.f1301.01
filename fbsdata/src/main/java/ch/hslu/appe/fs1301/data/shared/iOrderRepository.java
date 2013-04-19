package ch.hslu.appe.fs1301.data.shared;

import java.util.List;

import ch.hslu.appe.fs1301.data.shared.entity.Bestellung;

/**
 * Handels all calls to the entity Bestellung.
 * @author Sandro Bollhalder
 */
public interface iOrderRepository extends iRepository<Bestellung> {
	/**
	 * Gets all orders with the provided ids.
	 * @param The ids of the order to get.
	 * @return List of all orders
	 * @throws IllegalArgumentException If no id is provided.
	 */
	public List<Bestellung> getOrdersById(int... ids) throws IllegalArgumentException;

}
