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
	 * @return The order or null if not successful.
	 * @throws AccessDeniedException If the user does not have enough rights to execute. 
	 */
	public DTOBestellung createNewOrder(int customerId, int source, List<DTOBestellposition> positions) throws AccessDeniedException;
	/**
	 * Gets all orders.
	 * @param ids The ids to get.
	 * @return A list of all the orders.
	 * @throws AccessDeniedException If the user does not have enough rights to execute. 
	 */
	public List<DTOBestellung> getOrders(int... ids) throws AccessDeniedException;
	/**
	 * Gets all positions.
	 * @param ids The ids to get.
	 * @return A list of all the order positions.
	 * @throws AccessDeniedException If the user does not have enough rights to execute. 
	 */
	public List<DTOBestellposition> getOrderPositions(int... ids) throws AccessDeniedException;
}
