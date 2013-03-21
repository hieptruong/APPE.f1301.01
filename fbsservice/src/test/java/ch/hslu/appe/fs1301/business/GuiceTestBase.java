package ch.hslu.appe.fs1301.business;

import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceTestBase {

	protected Injector fInjector;

	@Before
	public void setUp() {		
		fInjector = Guice.createInjector(new ServiceModule());
	}
}
