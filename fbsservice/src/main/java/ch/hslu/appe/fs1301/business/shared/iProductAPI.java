package ch.hslu.appe.fs1301.business.shared;

import java.util.List;

import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;

/**
 * The ProductAPI handels everything that has to do with products.
 * @author Sandro Bollhalder
 */
public interface iProductAPI {
	/**
	 * Get all products.
	 * @return A list of all products.
	 */
	public List<DTOProdukt> getAllProducts();
}
