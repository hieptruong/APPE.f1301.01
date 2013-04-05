package ch.hslu.appe.fs1301.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.appe.fs1301.data.shared.KorrespondenzTemplate;
import ch.hslu.appe.fs1301.data.shared.Person;
import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;

/**
 * @author Thomas Bomatter
 * Tests for the EntityFactor
 */
public class EntityFactoryTest extends BaseTestClass {
	
	private iAPPEEntityManager manager;
	
	@Override
	@Before
	public void setUp() {	
		manager = new APPEEntityManager();
	}
	
	@Test
	public void TestCreateEntity() throws InstantiationException, IllegalAccessException {
		Person person = manager.createEntityObject(Person.class);
		fillPerson(person);
		
		manager.saveEntityObject(person);
		assertNotEquals(0, person.getId());
		
		manager.deleteEntityObject(person);
	}
	
	@Test
	public void TestUpdateEntity() throws InstantiationException, IllegalAccessException {
		Person person = manager.createEntityObject(Person.class);
		fillPerson(person);
		
		manager.saveEntityObject(person);
		int id = person.getId();
		assertNotEquals(0, id);
		
		person.setEMail("Thomas.bomatter@bluewin.ch");
		manager.saveEntityObject(person);
		
		Person person2 = manager.getEntityObject(Person.class, person.getId());
		assertEquals(person.getEMail(), person2.getEMail());
		assertEquals(id, person2.getId());
		
		manager.deleteEntityObject(person2);
	}
	
	@Test
	public void TestDeleteEntity() throws InstantiationException, IllegalAccessException {
		Person person = manager.createEntityObject(Person.class);
		fillPerson(person);	
		
		manager.saveEntityObject(person);
		int id = person.getId();
		assertNotEquals(0, id);
		
		manager.deleteEntityObject(person);
		
		Person person2 = manager.getEntityObject(Person.class, person.getId());
		assertNull(person2);
	}
	
	@Test
	public void TestCustomQuery() {
		String query = "SELECT p FROM Person p";
		List<Person> personList = manager.executeQuery(query, Person.class);		
		assertNotNull(personList);		
	}
	
	@Test
	public void TestRollback() {
		KorrespondenzTemplate template = new KorrespondenzTemplate();
		template.setInhalt("Test");
		template.setTyp(1);
		manager.startTransaction();
		manager.persist(template);
		manager.rollbackTransaction();
		
		KorrespondenzTemplate object = manager.getEntityObject(KorrespondenzTemplate.class,template.getId());
		assertNull(object);
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
		person.setAktiv(false);
	}
}
