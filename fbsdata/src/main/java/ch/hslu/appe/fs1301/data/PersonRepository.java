package ch.hslu.appe.fs1301.data;

import com.google.inject.Inject;

import ch.hslu.appe.fs1301.data.shared.Person;
import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;

public class PersonRepository extends BaseRepository<Person> implements
		iPersonRepository {

	@Inject
	public PersonRepository(iAPPEEntityManager entityManager) {
		super(entityManager);		
	}

}
