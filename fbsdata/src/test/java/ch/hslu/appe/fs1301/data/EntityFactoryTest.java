package ch.hslu.appe.fs1301.data;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.appe.fs1301.data.shared.Person;


public class EntityFactoryTest extends BaseTestClass {
	
	private APPEEntityManager fEntityFactory;

	@Override
	@Before
	public void setUp() {
		fEntityFactory = APPEEntityManager.getInstance();
	}
	
	@Test
	public void TestCreateEntity() throws InstantiationException, IllegalAccessException {
		Person person = fEntityFactory.createEntityObject(Person.class);
		fillPerson(person);
		
		fEntityFactory.saveEntityObject(person);
		assertNotEquals(0, person.getId());
		
		fEntityFactory.deleteEntityObject(person);
	}
	
	@Test
	public void TestUpdateEntity() throws InstantiationException, IllegalAccessException {
		Person person = fEntityFactory.createEntityObject(Person.class);
		fillPerson(person);
		
		fEntityFactory.saveEntityObject(person);
		int id = person.getId();
		assertNotEquals(0, id);
		
		person.setEMail("Thomas.bomatter@bluewin.ch");
		fEntityFactory.saveEntityObject(person);
		
		Person person2 = fEntityFactory.getEntityObject(Person.class, person.getId());
		assertEquals(person.getEMail(), person2.getEMail());
		assertEquals(id, person2.getId());
		
		fEntityFactory.deleteEntityObject(person2);
	}
	
	@Test
	public void TestDeleteEntity() throws InstantiationException, IllegalAccessException {
		Person person = fEntityFactory.createEntityObject(Person.class);
		fillPerson(person);	
		
		fEntityFactory.saveEntityObject(person);
		int id = person.getId();
		assertNotEquals(0, id);
		
		fEntityFactory.deleteEntityObject(person);
		
		Person person2 = fEntityFactory.getEntityObject(Person.class, person.getId());
		assertNull(person2);
	}
	
	public void fillPerson(Person person) {
		person.setEMail("Stefan.bachmann@lolz.ch");
		person.setName("Bachmann");
		person.setVorname("Stefan");
		person.setBenutzername("BaSt");
		person.setOrt("Malters");
		person.setPlz(6102);
		person.setGeburtstag(new Date());
		person.setRolle(0);
		person.setStrasse("Gartenstrasse 16");
		person.setPasswort("uhehwempti");
		person.setAktiv(0);
	}
}
