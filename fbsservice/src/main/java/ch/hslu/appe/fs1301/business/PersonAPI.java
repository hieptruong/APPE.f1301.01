package ch.hslu.appe.fs1301.business;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOConverter;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.iTransaction;
import ch.hslu.appe.fs1301.data.shared.entity.Person;

public class PersonAPI extends BaseAPI implements iPersonAPI {

	private iPersonRepository fPersonRepository;

	@Inject
	public PersonAPI(iPersonRepository personRepository, iTransaction transaction, iInternalSessionAPI sessionAPI) {
		super(transaction, sessionAPI);
		fPersonRepository = personRepository;
	}
	
	@Override
	public DTOPerson getCustomerById(int id) throws AccessDeniedException {
		checkRole(UserRole.SYSUSER | UserRole.ADMIN);
		Person person = fPersonRepository.getById(id);
		return person != null ? new DTOPerson(person) : null;
	}
	
	@Override
	public List<DTOPerson> getCustomersByName(String name) throws AccessDeniedException {
		checkRole(UserRole.SYSUSER | UserRole.ADMIN);
		List<Person> searchList;
		try {
			searchList = fPersonRepository.getPersonsByNames(name.split(" "));
		} catch(IllegalArgumentException exception) {
			searchList = new ArrayList<Person>(0);
		}
		
		return DTOConverter.convertPerson(searchList);
	}

	@Override
	public DTOPerson saveCustomer(DTOPerson person) throws AccessDeniedException {
		checkRole(UserRole.SYSUSER | UserRole.ADMIN);
		
		Person entity = person.getId() != 0 ? fPersonRepository.getById(person.getId()) : null;
		if (entity == null) {
			entity = DTOPerson.createNewPersonFromDTO(person);
		} else {
			DTOPerson.updatePersonFromDTO(entity, person);
		}
		
		fPersonRepository.saveObject(entity);
		DTOPerson newPerson = new DTOPerson(entity);
		return newPerson;
	}
}
