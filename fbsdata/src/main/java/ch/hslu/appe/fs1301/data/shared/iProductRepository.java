package ch.hslu.appe.fs1301.data.shared;

import java.util.List;

import ch.hslu.appe.fs1301.data.shared.entity.Produkt;

/**
 * Handels all calls to the entity Produkt.
 * @author Sandro Bollhalder
 */
public interface iProductRepository extends iRepository<Produkt>{
	/**
	 * Gets all products.
	 * @return A list of all products.
	 */
	public List<Produkt> getAllProducts(); 
}
