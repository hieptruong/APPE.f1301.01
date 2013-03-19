package ch.hslu.appe.fs1301.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.appe.fs1301.business.shared.iSessionAPI;

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
		assertTrue(sessionApi.authenticate("User", "user"));
		assertFalse(sessionApi.authenticate("Customer", "customer"));
		assertFalse(sessionApi.authenticate("Inaktiv", "inaktiv"));
	}
}
