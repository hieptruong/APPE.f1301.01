package ch.hslu.appe.fs1301.data;

import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.entity.Person;

public class PersonRepositoryTest extends BaseTestClass {

	private PersonRepository fTestee;
	private iAPPEEntityManager fEntityManagerMock;
	
	@Override
	public void setUp() {
		fEntityManagerMock = PowerMock.createMock(iAPPEEntityManager.class);
		fTestee = new PersonRepository(fEntityManagerMock);
	}
	
	@Test
	public void returnsNull_WhenQueryReturnsNoPersons() {
		setupReturnValueOfQuery();
		PowerMock.replayAll();
		
		Person returnedPerson = fTestee.getPersonByUsernameAndPassword("username", "password");
		
		assertThat(returnedPerson).isNull();
	}
	
	@Test
	public void returnsPerson_WhenQueryReturnsOnePerson() {
		Person expectedPerson = new Person();
		setupReturnValueOfQuery(expectedPerson);
		PowerMock.replayAll();
		
		Person returnedPerson = fTestee.getPersonByUsernameAndPassword("username", "password");
		
		assertThat(returnedPerson).isSameAs(expectedPerson);
	}
	
	@Test
	public void returnsFirstPerson_WhenQueryReturnsMultiplePersons() {
		Person expectedPerson = new Person();
		Person notExpectedPerson = new Person();
		setupReturnValueOfQuery(expectedPerson, notExpectedPerson);
		PowerMock.replayAll();
		
		Person returnedPerson = fTestee.getPersonByUsernameAndPassword("username", "password");
		
		assertThat(returnedPerson).isSameAs(expectedPerson);
		assertThat(returnedPerson).isNotSameAs(notExpectedPerson);
	}
	
	private void setupReturnValueOfQuery(Person... persons) {
		ArrayList<Person> ExpectedReturnValue = new ArrayList<Person>();
		for(Person person : persons)
			ExpectedReturnValue.add(person);
		expect(fEntityManagerMock.executeQuery(isA(String.class), eq(Person.class))).andReturn(ExpectedReturnValue);
	}
}
