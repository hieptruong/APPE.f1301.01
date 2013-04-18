package ch.hslu.appe.fs1301.data;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.entity.Person;

public class PersonRepositoryTest extends BaseTestClass {

	private final static String ValidUsername = "ValidUsername";
	private final static String DoubleUsername = "DoubleUsername";
	private final static String ValidPassword = "ValidPassword";
	private final static String InValidUsername = "InValidUsername";
	private final static String InValidPassword = "InValidPassword";
	private final static String FirstNameOne = "FirstNameOne";
	private final static String FirstNameTwo = "FirstNameTwo";
	private final static String LastName = "LastName";
	
	
	private PersonRepository fTestee;
	private static iAPPEEntityManager fEntityManager;
	private static List<Person> fCreatedPersons;
	
	@BeforeClass
	public static void setUpDatabase() {
		fCreatedPersons = new ArrayList<Person>();
		fEntityManager = new APPEEntityManager();
		fEntityManager.beginTransaction();
		fCreatedPersons.add(createAndSavePerson(ValidUsername, ValidPassword, FirstNameOne, LastName));
		fCreatedPersons.add(createAndSavePerson(DoubleUsername, ValidPassword, FirstNameOne, LastName));
		fCreatedPersons.add(createAndSavePerson(DoubleUsername, ValidPassword, FirstNameTwo, FirstNameTwo));
	}
	
	@AfterClass
	public static void tearDownDatabase() {
		fEntityManager.rollbackTransaction();
	}
	
	@Override
	public void setUp() {
		fTestee = new PersonRepository(fEntityManager);
	}
	
	@Test
	public void returnsNull_WhenQueryReturnsNoPersons() {
		Person returnedPerson = fTestee.getPersonByUsernameAndPassword(InValidUsername, InValidPassword);
		
		assertThat(returnedPerson).isNull();
	}
	
	@Test
	public void returnsPerson_WhenQueryReturnsOnePerson() {
		Person returnedPerson = fTestee.getPersonByUsernameAndPassword(ValidUsername, ValidPassword);
		
		assertThat(returnedPerson.getBenutzername()).isSameAs(ValidUsername);
		assertThat(returnedPerson.getPasswort()).isSameAs(ValidPassword);
	}
	
	@Test
	public void returnsFirstPerson_WhenQueryReturnsMultiplePersons() {
		Person returnedPerson = fTestee.getPersonByUsernameAndPassword(DoubleUsername, ValidPassword);
		
		assertThat(returnedPerson.getBenutzername()).isSameAs(DoubleUsername);
		assertThat(returnedPerson.getPasswort()).isSameAs(ValidPassword);
	}
	
	@Test
	public void returnsPersons_WhenSearchWithOneName(){
		List<Person> returnedPersons = fTestee.getPersonsByNames(FirstNameOne);
		
		assertThat(returnedPersons).contains(fCreatedPersons.get(0), fCreatedPersons.get(1));
	}
	
	@Test
	public void returnsPersons_WhenSearchWithMultipleNames() {
		List<Person> returnedPersons = fTestee.getPersonsByNames(FirstNameOne, LastName);
		
		assertThat(returnedPersons).contains(fCreatedPersons.get(0), fCreatedPersons.get(1));
	}
	
	@Test
	public void returnsNoPersons_WhenSearchWithNotExistingName(){
		List<Person> returnedPersons = fTestee.getPersonsByNames(InValidUsername);
		
		assertThat(returnedPersons).isEmpty();
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void throwsException_WhenSearchWithNoName() {
		fTestee.getPersonsByNames();
	}
	
	private static Person createAndSavePerson(String username, String password, String firstname, String lastname) {
		Person person = new Person();
		person.setBenutzername(username);
		person.setPasswort(password);
		person.setVorname(firstname);
		person.setName(lastname);
		
		setIrrelevantValues(person);
		fEntityManager.persist(person);
		return person;
	}
	
	private static void setIrrelevantValues(Person person) {
		person.setEMail("Test");
		person.setOrt("Test");
		person.setPlz(1);
		person.setGeburtstag(new Date());
		person.setRolle(0);
		person.setStrasse("Test");
		person.setAktiv(false);
	}
}
