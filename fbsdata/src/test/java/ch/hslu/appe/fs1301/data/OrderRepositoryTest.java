package ch.hslu.appe.fs1301.data;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellung;
import ch.hslu.appe.fs1301.data.shared.entity.Person;

public class OrderRepositoryTest {
	private OrderRepository fTestee;
	private static iAPPEEntityManager fEntityManager;
	private static List<Bestellung> fCreatedOrder;
	private static Person fPerson;
	
	@BeforeClass
	public static void setUpDatabase() {
		fCreatedOrder = new ArrayList<Bestellung>();
		fEntityManager = new APPEEntityManager();
		fEntityManager.beginTransaction();
		fPerson = createAndSaveDummyPerson();
		fCreatedOrder.add(createAndSaveOrder());
		fCreatedOrder.add(createAndSaveOrder());
	}

	@AfterClass
	public static void tearDownDatabase() {
		fEntityManager.rollbackTransaction();
	}
	
	@Before
	public void setUp() {
		fTestee = new OrderRepository(fEntityManager);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void throwsException_WhenSearchWithNoId() {
		fTestee.getOrdersById();
	}
	
	@Test
	public void returnsNoOrders_WhenSearchWithNotExistingId(){
		List<Bestellung> returnedOrders = fTestee.getOrdersById(Integer.MAX_VALUE);
		
		assertThat(returnedOrders).isEmpty();
	}
	
	@Test
	public void returnsOrder_WhenSearchWithOneId() {
		List<Bestellung> returnedOrders = fTestee.getOrdersById(fCreatedOrder.get(0).getId());
		
		assertThat(returnedOrders).contains(fCreatedOrder.get(0));
	}
	
	private static void setIrrelevantValues(Bestellung bestellung) {
		bestellung.setPerson1(fPerson);
		bestellung.setPerson2(fPerson);
	}

	private static Bestellung createAndSaveOrder() {
		Bestellung bestellung = new Bestellung();
		bestellung.setBestelldatum(new Date());
		bestellung.setLiefertermin_Ist(new Date());
		bestellung.setLiefertermin_Soll(new Date());
		bestellung.setQuelle(1);
		
		setIrrelevantValues(bestellung);
		fEntityManager.persist(bestellung);
		return bestellung;
	}
	
	private static Person createAndSaveDummyPerson() {
		Person person = new Person();
		person.setBenutzername("username");
		person.setPasswort("password");
		person.setVorname("firstname");
		person.setName("lastname");
		person.setEMail("Test");
		person.setOrt("Test");
		person.setPlz(1);
		person.setGeburtstag(new Date());
		person.setRolle(0);
		person.setStrasse("Test");
		person.setAktiv(false);
		fEntityManager.persist(person);
		return person;
	}
}
