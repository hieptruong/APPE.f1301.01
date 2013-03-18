package ch.hslu.appe.fs1301.business;

import ch.hslu.appe.fs1301.business.shared.iSessionAPI;

public class SessionAPI implements iSessionAPI {

	@Override
	public boolean isAuthenticated() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasRole(int role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean authenticate(String login, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
