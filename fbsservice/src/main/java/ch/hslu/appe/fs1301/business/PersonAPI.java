package ch.hslu.appe.fs1301.business;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOConverter;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.entity.Person;

public class PersonAPI implements iPersonAPI {

	private iPersonRepository fPersonRepository;

	@Inject
	public PersonAPI(iPersonRepository personRepository) {
		fPersonRepository = personRepository;
	}
	
	@Override
	public DTOPerson getCustomerById(int id) {
		Person person = fPersonRepository.getById(id);
		return person != null ? new DTOPerson(person) : null;
	}
	
	@Override
	public List<DTOPerson> getCustomersByName(String name) {
		List<Person> searchList;
		try {
			searchList = fPersonRepository.getPersonsByNames(name.split(" "));
		} catch(IllegalArgumentException exception) {
			searchList = new ArrayList<Person>(0);
		}
		
		return DTOConverter.convertPerson(searchList);
	}
}
