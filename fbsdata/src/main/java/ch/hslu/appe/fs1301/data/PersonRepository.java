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

	@Override
	public Person getPersonByUsernameAndPassword(String username, String password) {
		StringBuilder query = new StringBuilder(100);
		query.append("SELECT p FROM Person p WHERE p.benutzername = '");
		query.append(username);
		query.append("' and p.passwort = '");
		query.append(password);
		query.append('\'');
		
		List<Person> resultList = executeQuery(query);
		
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	@Override
	public List<Person> getPersonsByNames(String... names) {
		if (names.length == 0)
			throw new IllegalArgumentException("names");
		
		StringBuilder query = new StringBuilder(100);
		query.append("SELECT p FROM Person p WHERE ");
		for(String name : names) {
			query.append("((p.vorname like '%")
				 .append(name)
				 .append("%') OR (p.name like '%")
				 .append(name)
				 .append("%')) AND ");
		}
		
		query.delete(query.length()-4, query.length());
		
		return executeQuery(query);
	}
}
