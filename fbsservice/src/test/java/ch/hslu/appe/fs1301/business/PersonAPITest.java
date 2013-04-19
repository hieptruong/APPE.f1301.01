package ch.hslu.appe.fs1301.business;

import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.IExpectationSetters;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.iTransaction;
import ch.hslu.appe.fs1301.data.shared.entity.Person;

public class PersonAPITest {
	private iPersonAPI fTestee;
	private iPersonRepository fPersonRepositoryMock;
	private iInternalSessionAPI fSessionMock;
	private iTransaction fTransactionMock;
	
	@Before
	public void setUp() {	
		fPersonRepositoryMock = PowerMock.createMock(iPersonRepository.class);
		fSessionMock = PowerMock.createMock(iInternalSessionAPI.class);
		fTransactionMock = PowerMock.createMock(iTransaction.class);
		fTestee = new PersonAPI(fPersonRepositoryMock, fTransactionMock, fSessionMock);		
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
	
	@Test(expected = AccessDeniedException.class)
	public void needsSysUser_OnSaveCustomer() throws AccessDeniedException {
		final int ExpectedRole = UserRole.SYSUSER;
		
		//Check against role
		expect(fSessionMock.hasRole(ExpectedRole)).andReturn(false);
		PowerMock.replayAll();
		
		fTestee.saveCustomer(null);
	}
	
	@Test
	public void createsANewPerson_OnSaveCustomer_WhenIdIsZero() throws AccessDeniedException {
		setupCheckRoleIrrelevant();
		
		DTOPerson expectedPerson = new DTOPerson();
		expectedPerson.setBenutzername("UserName");
		Capture<Person> cap = new Capture<Person>();
		fPersonRepositoryMock.saveObject(capture(cap));
		PowerMock.replayAll();
		
		DTOPerson result = fTestee.saveCustomer(expectedPerson);
		
		assertThat(cap.getValue().getBenutzername()).isEqualTo(expectedPerson.getBenutzername());
		assertThat(result.getBenutzername()).isEqualTo(expectedPerson.getBenutzername());
	}

	@Test
	public void updatesPerson_OnSaveCustomer_WhenIdIsKnown() throws AccessDeniedException {
		final int ExpectedId = 10;
		
		setupCheckRoleIrrelevant();
		
		DTOPerson expectedPerson = new DTOPerson();
		expectedPerson.setId(ExpectedId);
		expectedPerson.setBenutzername("UserName");
		
		Person returnedPerson = new Person();		
		expect(fPersonRepositoryMock.getById(ExpectedId)).andReturn(returnedPerson);
		
		Capture<Person> cap = new Capture<Person>();
		fPersonRepositoryMock.saveObject(capture(cap));
		PowerMock.replayAll();
		
		DTOPerson result = fTestee.saveCustomer(expectedPerson);
		
		assertThat(cap.getValue().getBenutzername()).isEqualTo(expectedPerson.getBenutzername());
		assertThat(result.getBenutzername()).isEqualTo(expectedPerson.getBenutzername());
	}
	
	private void setupCheckRoleIrrelevant() {
		expect(fSessionMock.hasRole(EasyMock.anyInt())).andReturn(true);
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
