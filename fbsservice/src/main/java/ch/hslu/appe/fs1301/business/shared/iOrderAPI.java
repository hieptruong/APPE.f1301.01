package ch.hslu.appe.fs1301.business.shared;

import java.util.List;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;

/**
 * The PersonAPI handels everything that has to do with ordering.
 * @author Sandro Bollhalder
 */
public interface iOrderAPI {

	/**
	 * Creates a new order with the provided information.
	 * @param customerId The customer to attach the order.
	 * @param source The ordering source. {@link OrderSource}. 
	 * @param positions All order positions.
	 * @return True if successful, false otherwise.
	 */
	public boolean createNewOrder(int customerId, int source, List<DTOBestellposition> positions);
	/**
	 * Gets all orders.
	 * @param ids The ids to get.
	 * @return A list of all the orders.
	 */
	public List<DTOBestellung> getOrders(int... ids);
	/**
	 * Gets all positions.
	 * @param ids The ids to get.
	 * @return A list of all the order positions.
	 */
	public List<DTOBestellposition> getOrderPositions(int... ids);
}
