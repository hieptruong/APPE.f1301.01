package ch.hslu.appe.fs1301.data;

import java.util.List;

import com.google.inject.Inject;

import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.entity.Person;

public class PersonRepository extends BaseRepository<Person> implements iPersonRepository {

	@Inject
	public PersonRepository(iAPPEEntityManager entityManager) {
		super(entityManager);		
	}

	public Person getPersonByUsernameAndPassword(String username, String password) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT p FROM Person p WHERE p.benutzername = '");
		query.append(username);
		query.append("' and p.passwort = '");
		query.append(password);
		query.append("'");
		
		List<Person> resultList = executeQuery(query);
		
		return resultList.isEmpty() ? null : resultList.get(0);
	}
}
