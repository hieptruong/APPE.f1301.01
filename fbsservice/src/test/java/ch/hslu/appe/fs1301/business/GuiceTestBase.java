package ch.hslu.appe.fs1301.business;

import org.junit.Before;

import ch.hslu.appe.fs1301.business.shared.ServiceModule;
import ch.hslu.appe.fs1301.data.shared.DataModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceTestBase {

	protected Injector fInjector;

	@Before
	public void setUp() {		
		fInjector = Guice.createInjector(new ServiceModule(), new DataModule());
	}
}
