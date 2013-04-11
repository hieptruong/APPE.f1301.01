package ch.hslu.appe.fs1301.data;

import static org.junit.Assert.*;

import java.util.Date;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.entity.Person;

public class RepositoryTest extends BaseTestClass {

	private iPersonRepository personRepo;

	@Override
	@Before
	public void setUp() {
		personRepo = new PersonRepository(new APPEEntityManager());
	}
	
	@Test
	public void returnsNull_WhenIdIsNotFound() {
		Person person = personRepo.getById(Integer.MAX_VALUE);
		assertThat(person).isNull();
	}
	
	@Test
	public void TestSaveAndGetById() {
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
		
		personRepo.saveObject(person);		
		Person person2 = personRepo.getById(person.getId());
		
		assertEquals(person.getVorname(), person2.getVorname());
		assertEquals(person.getId(), person2.getId());
		assertEquals(person.getEMail(), person2.getEMail());
		assertEquals(person.getOrt(), person2.getOrt());
	}
}
