package ch.hslu.appe.fs1301.business;

import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.entity.Person;

import com.google.inject.Inject;

/**
 * @author Thomas Bomatter
 * SessionAPI handles Login and provides the User after login.
 */
public class SessionAPI implements iInternalSessionAPI {

	private iPersonRepository fPersonRepository;
	private Person fUser;
	
	@Inject	
	public SessionAPI(iPersonRepository personRepository) {
		fPersonRepository = personRepository;
	}
	
	@Override
	public boolean isAuthenticated() {
		return fUser != null;
	}

	@Override
	public String getUserName() {
		if (isAuthenticated()) {
			return fUser.getBenutzername();
		}
		return null;
	}

	@Override
	public boolean hasRole(int role) {
		if (isAuthenticated()) {
			if ((fUser.getRolle() & role) != 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean authenticate(String login, String password) {
		fUser = fPersonRepository.getPersonByUsernameAndPassword(login, password);
		if (fUser == null) {
			return false;
		} else {
			if (hasRole(UserRole.ADMIN | UserRole.SYSUSER) && fUser.getAktiv()) {
				return true;
			} else {
				fUser = null;
				return false;
			}			
		}
	}
	
	@Override
	public void logout() {
		fUser = null;
	}

	@Override
	public Person getAuthenticatedUser() {
		return fUser;
	}
}
