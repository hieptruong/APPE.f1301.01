package ch.hslu.appe.fs1301.business.shared;


/**
 * The SessionAPI handels everything which has to do with login, rights or the name. 
 * @author Sandro bollhalder
 */
public interface iSessionAPI {	
	/**
	 * Checks whether a current user is authenticated.
	 * @return If current user is authenticated.
	 */
	public boolean isAuthenticated();	
	/**
	 * Gets the username of the authenticated user.
	 * @return The username.
	 */
	public String getUserName();
	/**
	 * Checks if the authenticated user has the provided role.
	 * @param role The role to check against.
	 * @return True if it has the role, false otherwise.
	 */
	public boolean hasRole(int role);
	/**
	 * Tries to login with the provided credentials.
	 * @param login The username.
	 * @param password The password.
	 * @return True if login successful, false otherwise.
	 */
	public boolean authenticate(String login, String password);
	/**
	 * Logout of the current user or nothing if not authenticated.
	 */
	public void logout();
}
