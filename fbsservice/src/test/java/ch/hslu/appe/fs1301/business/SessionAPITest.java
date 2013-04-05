package ch.hslu.appe.fs1301.business;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;

import static org.easymock.EasyMock.*;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1301.business.shared.iSessionAPI;
import ch.hslu.appe.fs1301.data.shared.Person;
import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;

/**
 * @author Thomas Bomatter
 * Session API Test
 */
public class SessionAPITest {
	private iSessionAPI fTestee;
	private iAPPEEntityManager fAPPEEntityManagerMock;
	
	@Before
	public void setUp() {	
		fAPPEEntityManagerMock = PowerMock.createMock(iAPPEEntityManager.class);
		fTestee = new SessionAPI(fAPPEEntityManagerMock);		
	}
	
	@Test
	public void isNotAuthenticatedOnStartup() {
		assertThat(fTestee.isAuthenticated()).isFalse();
		assertThat(fTestee.getUserName()).isNullOrEmpty();
		assertThat(fTestee.hasRole(UserRole.CUSTOMER)).isFalse();
	}
	
	@Test
	public void usernameIsSetOnSuccessfulLogin()
	{
		final String expectedUserName = "UserName";
		
		Person person = new Person();
		person.setBenutzername(expectedUserName);
		person.setRolle(UserRole.ADMIN);
		person.setAktiv(true);
		
		setupReturnValueOfQuery(person);
		
		PowerMock.replayAll();
		fTestee.authenticate(expectedUserName, "");
		assertThat(fTestee.getUserName()).isEqualTo(expectedUserName);
	}
	
	@Test
	public void successfulLoginWhenRoleIsAdminAndIsActive(){
		
		Person person = new Person();
		person.setRolle(UserRole.ADMIN);
		person.setAktiv(true);
		
		setupReturnValueOfQuery(person);
		
		PowerMock.replayAll();
		boolean result = fTestee.authenticate("", "");
		assertThat(result).isTrue();
		assertThat(fTestee.isAuthenticated()).isTrue();
	}
	
	@Test
	public void successfulLoginWhenRoleIsSysuserAndIsActive(){
		
		Person person = new Person();
		person.setRolle(UserRole.SYSUSER);
		person.setAktiv(true);
		
		setupReturnValueOfQuery(person);
		
		PowerMock.replayAll();
		boolean result = fTestee.authenticate("", "");
		assertThat(result).isTrue();
		assertThat(fTestee.isAuthenticated()).isTrue();
	}
	
	@Test
	public void unsuccessfulLoginWhenRoleIsCustomerAndIsActive(){
		
		Person person = new Person();
		person.setRolle(UserRole.CUSTOMER);
		person.setAktiv(true);
		
		setupReturnValueOfQuery(person);
		
		PowerMock.replayAll();
		boolean result = fTestee.authenticate("", "");
		assertThat(result).isFalse();
		assertThat(fTestee.isAuthenticated()).isFalse();
	}
	
	@Test
	public void unsuccessfulLoginWhenRoleIsAdminAndIsInActive(){
		
		Person person = new Person();
		person.setRolle(UserRole.ADMIN);
		person.setAktiv(false);
		
		setupReturnValueOfQuery(person);
		
		PowerMock.replayAll();
		boolean result = fTestee.authenticate("", "");
		assertThat(result).isFalse();
		assertThat(fTestee.isAuthenticated()).isFalse();
	}

	@Test
	public void unsuccessfulLoginWhenRoleIsNoneAndIsInActive(){
		
		Person person = new Person();
		person.setRolle(UserRole.NONE);
		person.setAktiv(false);
		
		setupReturnValueOfQuery(person);
		
		PowerMock.replayAll();
		boolean result = fTestee.authenticate("", "");
		assertThat(result).isFalse();
		assertThat(fTestee.isAuthenticated()).isFalse();
	}
	

	@Test
	public void unsuccessfulLoginWhenNoUserIsReturned(){
		setupReturnValueOfQuery(null);
		
		PowerMock.replayAll();
		boolean result = fTestee.authenticate("", "");
		assertThat(result).isFalse();
		assertThat(fTestee.isAuthenticated()).isFalse();
	}
	
	private void setupReturnValueOfQuery(Person person) {
		ArrayList<Person> ExpectedReturnValue = new ArrayList<Person>();
		if (person != null)
			ExpectedReturnValue.add(person);
		expect(fAPPEEntityManagerMock.executeQuery(isA(String.class), eq(Person.class))).andReturn(ExpectedReturnValue);
	}
}
