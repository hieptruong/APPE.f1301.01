package ch.hslu.appe.fs1301.business;

import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.easymock.IExpectationSetters;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.entity.Person;

public class PersonAPITest {
	private iPersonAPI fTestee;
	private iPersonRepository fPersonRepositoryMock;
	
	@Before
	public void setUp() {	
		fPersonRepositoryMock = PowerMock.createMock(iPersonRepository.class);
		fTestee = new PersonAPI(fPersonRepositoryMock);		
	}
	
	@Test
	public void returnsPerson_OnGetCustomerById() {
		final int ExpectedId = 10;
		Person returnedPerson = new Person();
		returnedPerson.setId(ExpectedId);
		
		expect(fPersonRepositoryMock.getById(eq(ExpectedId))).andReturn(returnedPerson);
		PowerMock.replayAll();
		
		DTOPerson result = fTestee.getCustomerById(ExpectedId);
		assertThat(result.getId()).isSameAs(ExpectedId);
	}
	
	@Test
	public void returnsNull_OnGetCustomerById_WhenNullIsReturnedByRepo() {
		final int Id = 10;
		expect(fPersonRepositoryMock.getById(eq(Id))).andReturn(null);
		PowerMock.replayAll();
		
		DTOPerson result = fTestee.getCustomerById(Id);
		assertThat(result).isNull();
	}
	
	@Test
	public void returnsAnEmptyList_OnGetCustomersByName_WhenAnIllegalArgumentExceptionIsThrown() {
		expect(fPersonRepositoryMock.getPersonsByNames(eq(""))).andThrow(new IllegalArgumentException());
		PowerMock.replayAll();
		
		List<DTOPerson> resultList = fTestee.getCustomersByName("");
		assertThat(resultList).isEmpty();
	}
	
	@Test
	public void returnsListAsDTO_OnGetCustomersByName_WithASingleName(){
		final String SingleName = "someName";
		
		List<Person> jpaList = createPersonsById(1, 2);
		setupReturnValueOfGetCustomersByName(jpaList, SingleName);
		PowerMock.replayAll();
		
		List<DTOPerson> resultList = fTestee.getCustomersByName(SingleName);
		
		
		for(int i = 0; i < jpaList.size(); i++) {
			assertThat(resultList.get(i).getId()).isSameAs(jpaList.get(i).getId());
		}
	}
	
	@Test
	public void returnsListAsDTO_OnGetCustomersByName_WithAMultipleNames(){
		final String NameOne = "Name";
		final String NameTwo = "Name2";
		
		List<Person> jpaList = createPersonsById(1, 2);
		setupReturnValueOfGetCustomersByName(jpaList, NameOne, NameTwo);
		PowerMock.replayAll();
		
		List<DTOPerson> resultList = fTestee.getCustomersByName(NameOne + " " + NameTwo);
		
		
		for(int i = 0; i < jpaList.size(); i++) {
			assertThat(resultList.get(i).getId()).isSameAs(jpaList.get(i).getId());
		}
	}
	
	private List<Person> createPersonsById(int... ids){
		List<Person> list = new ArrayList<Person>(ids.length);
		for(int id : ids) {
			Person person = new Person();
			person.setId(id);
			list.add(person);
		}
		return list;		
	}
	
	private void setupReturnValueOfGetCustomersByName(List<Person> persons, String... names) {
		IExpectationSetters<List<Person>> expection;
		switch(names.length) {
		case 0: expection = expect(fPersonRepositoryMock.getPersonsByNames()); break;
		case 1: expection = expect(fPersonRepositoryMock.getPersonsByNames(eq(names[0]))); break;
		case 2: expection = expect(fPersonRepositoryMock.getPersonsByNames(eq(names[0]), eq(names[1]))); break;
		default: return;
		}
		
		expection.andReturn(persons);
	}
}
