package ch.hslu.appe.fs1301.business.shared;

import java.util.List;

import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;

/**
 * The PersonAPI handels everything that has to do with persons.
 * @author bollha
 *
 */
public interface iPersonAPI {
	/**
	 * Gets all customers by name. (Either first or lastname)
	 * @param name The first and/or last name.
	 * @return The resultlist of the search.
	 */
	public List<DTOPerson> getCustomersByName(String name);
}
