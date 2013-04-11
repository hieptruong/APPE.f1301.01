package ch.hslu.appe.fs1301.data.shared;

import ch.hslu.appe.fs1301.data.shared.entity.Person;

/**
 * @author Thomas Bomatter
 * iPersonRepository
 */
public interface iPersonRepository extends iRepository<Person> {
	public Person getPersonByUsernameAndPassword(String username, String password);
}
