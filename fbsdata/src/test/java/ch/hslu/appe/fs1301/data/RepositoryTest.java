package ch.hslu.appe.fs1301.data;

import static org.junit.Assert.*;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.entity.Person;

public class RepositoryTest extends BaseTestClass {
	
	private iPersonRepository fPersonRepo;
	private iAPPEEntityManager fEntityManager;

	@Override
	@Before
	public void setUp() {
		fEntityManager = new APPEEntityManager();
		fPersonRepo = new PersonRepository(fEntityManager);
	}
	
	@Test
	public void returnsNull_WhenIdIsNotFound() {
		Person person = fPersonRepo.getById(Integer.MAX_VALUE);
		assertThat(person).isNull();
	}
	
	@Test
	public void TestSaveAndGetById() {
		Person person = createPerson();
		
		fPersonRepo.saveObject(person);		
		Person person2 = fPersonRepo.getById(person.getId());
		
		assertEquals(person.getVorname(), person2.getVorname());
		assertEquals(person.getId(), person2.getId());
		assertEquals(person.getEMail(), person2.getEMail());
		assertEquals(person.getOrt(), person2.getOrt());
		
		fPersonRepo.deleteObject(person);
		person2 = fPersonRepo.getById(person.getId());
		assertEquals(null, person2);
	}
	
	@Test
	public void PersistsObject() {
		fEntityManager.beginTransaction();
		try {
			Person person = createPerson();
			fPersonRepo.persistObject(person);
			
			Person person2 = fPersonRepo.getById(person.getId());
			assertEquals(person.getId(), person2.getId());
			
			fEntityManager.rollbackTransaction();
			person2 = fPersonRepo.getById(person.getId());
			assertEquals(null, person2);
		} catch (Exception exception) {
			fEntityManager.rollbackTransaction();
		}
	}
	
	private Person createPerson() {
		Person person = new Person();
		person.setEMail("testee@test.ch");
		person.setName("testee");
		person.setVorname("test");
		person.setBenutzername("test");
		person.setOrt("testington");
		person.setPlz(1111);
		person.setGeburtstag(new Date());
		person.setRolle(0);
		person.setStrasse("testroad 13");
		person.setPasswort("testee");
		person.setAktiv(false);
		return person;
	}
}
