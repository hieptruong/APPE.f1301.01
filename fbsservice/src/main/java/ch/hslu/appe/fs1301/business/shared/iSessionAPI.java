package ch.hslu.appe.fs1301.business.shared;

public interface iSessionAPI {	
	public boolean isAuthenticated();	
	public String getUserName();
	public boolean hasRole(int role);
	
	public boolean authenticate(String login, String password);
}
