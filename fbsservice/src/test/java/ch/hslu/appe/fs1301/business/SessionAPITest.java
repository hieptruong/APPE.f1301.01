package ch.hslu.appe.fs1301.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.appe.fs1301.business.shared.iSessionAPI;
/**
 * @author Thomas Bomatter
 * Session API Test
 */
public class SessionAPITest extends GuiceTestBase {
	private iSessionAPI sessionApi;
	
	@Override
	@Before
	public void setUp() {	
		super.setUp();
		sessionApi = fInjector.getInstance(iSessionAPI.class);		
	}
	
	@Test
	public void TestLogin() {		
		assertTrue(sessionApi.authenticate("Admin", "admin"));
		assertEquals(sessionApi.getUserName(), "Admin");
		assertTrue(sessionApi.authenticate("User", "user"));
		assertEquals(sessionApi.getUserName(), "User");
		assertFalse(sessionApi.authenticate("Customer", "customer"));
		assertFalse(sessionApi.authenticate("Inaktiv", "inaktiv"));
	}
}
