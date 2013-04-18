package ch.hslu.appe.fs1301.data.shared;

import java.util.List;

import ch.hslu.appe.fs1301.data.shared.entity.Bestellposition;

/**
 * Handels all calls to the entity Bestellposition.
 * @author Sandro Bollhalder
 */
public interface iOrderPositionRepository extends iRepository<Bestellposition> {
	/**
	 * Gets all orderpostions with the provided ids.
	 * @param The wanted ids.
	 * @return A list of the wanted orderpositions.
	 * @throws IllegalArgumentException If no id is provided.
	 */
	public List<Bestellposition> getOrderPositionsById(int... ids) throws IllegalArgumentException;
}
