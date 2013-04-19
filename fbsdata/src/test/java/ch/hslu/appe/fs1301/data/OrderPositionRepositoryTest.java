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
import ch.hslu.appe.fs1301.data.shared.entity.Bestellposition;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellung;
import ch.hslu.appe.fs1301.data.shared.entity.Person;
import ch.hslu.appe.fs1301.data.shared.entity.Produkt;

public class OrderPositionRepositoryTest {
	private OrderPositionRepository fTestee;
	private static iAPPEEntityManager fEntityManager;
	private static List<Bestellposition> fCreatedPositions;
	private static Bestellung fOrder;
	private static Produkt fProduct;
	private static Person fPerson;
	
	@BeforeClass
	public static void setUpDatabase() {
		fCreatedPositions = new ArrayList<Bestellposition>();
		fEntityManager = new APPEEntityManager();
		fEntityManager.beginTransaction();
		fPerson = createAndSaveDummyPerson();
		fOrder = createAndSaveDummyOrder();
		fProduct = createAndSaveDummyProduct();
		fCreatedPositions.add(createAndSavePosition());
		fCreatedPositions.add(createAndSavePosition());
		fCreatedPositions.add(createAndSavePosition());
	}

	@AfterClass
	public static void tearDownDatabase() {
		fEntityManager.rollbackTransaction();
	}
	
	@Before
	public void setUp() {
		fTestee = new OrderPositionRepository(fEntityManager);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void throwsException_WhenSearchWithNoId() {
		fTestee.getOrderPositionsById();
	}
	
	@Test
	public void returnsNoPositions_WhenSearchWithNotExistingId(){
		List<Bestellposition> returnedPositions = fTestee.getOrderPositionsById(Integer.MAX_VALUE);
		
		assertThat(returnedPositions).isEmpty();
	}
	
	@Test
	public void returnsPosition_WhenSearchWithOneId() {
		List<Bestellposition> returnedPositions = fTestee.getOrderPositionsById(fCreatedPositions.get(0).getId());
		
		assertThat(returnedPositions).contains(fCreatedPositions.get(0));
	}
	
	private static Bestellposition createAndSavePosition() {
		Bestellposition position = new Bestellposition();
		
		setIrrelevantValues(position);
		fEntityManager.persist(position);
		return position;
	}

	private static void setIrrelevantValues(Bestellposition position) {
		position.setAnzahl(5);
		position.setProdukt(fProduct);
		position.setStueckpreis(100);
		position.setBestellung(fOrder);
	}

	private static Bestellung createAndSaveDummyOrder() {
		Bestellung bestellung = new Bestellung();
		bestellung.setBestelldatum(new Date());
		bestellung.setLiefertermin_Ist(new Date());
		bestellung.setLiefertermin_Soll(new Date());
		bestellung.setQuelle(1);
		bestellung.setPerson1(fPerson);
		bestellung.setPerson2(fPerson);
		fEntityManager.persist(bestellung);
		return bestellung;
	}
	
	private static Produkt createAndSaveDummyProduct() {
		Produkt product = new Produkt();
		product.setLagerbestand(50);
		product.setMinimalMenge(20);
		product.setPreis(100);
		product.setBezeichnung("TestProduct");
		fEntityManager.persist(product);
		return product;
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
