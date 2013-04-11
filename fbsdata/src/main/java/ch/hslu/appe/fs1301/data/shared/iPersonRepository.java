package ch.hslu.appe.fs1301.data.shared;

import java.util.List;

import ch.hslu.appe.fs1301.data.shared.entity.Person;

/**
 * @author Thomas Bomatter
 * iPersonRepository
 */
public interface iPersonRepository extends iRepository<Person> {
	/**
	 * Gets the first person or null for the given username / password.
	 * @param username The username.
	 * @param password The password.
	 * @return The found person.
	 */
	public Person getPersonByUsernameAndPassword(String username, String password);
	
	/**
	 * Gets a list of persons of which the first / last name contains the names.
	 * Example: ((first contains name1) or (last contains name1)) and ((first contains name2) or (last contains name2)). 
	 * @param names All names to be searched for.
	 * @return The resultlist of the search.
	 * @throws IllegalArgumentException If no name is provided.
	 */
	public List<Person> getPersonsByNames(String... names) throws IllegalArgumentException;
}
