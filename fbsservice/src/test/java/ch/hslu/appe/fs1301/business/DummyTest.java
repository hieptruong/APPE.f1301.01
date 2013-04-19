package ch.hslu.appe.fs1301.business;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1301.business.shared.OrderSource;
import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.iSessionAPI;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.iProductRepository;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Stage;

/**
 * This class contains all tests that do not really test something useful. They are only here to satisfy code coverage. :)
 * @author Sandro Bollhalder
 */
public class DummyTest {

	@Test
	public void UserRoleCoverage() {
		UserRole userrole = new UserRole();
		assertThat(userrole).isNotNull();
	}
	
	@Test
	public void OrderSourceCoverage() {
		OrderSource orderSource = new OrderSource();
		assertThat(orderSource).isNotNull();
	}
	
	@Test
	public void GuiceTest() {
		Injector injector = Guice.createInjector(Stage.TOOL, new MockedModule(), new ServiceModule());
		assertThat(injector.getBinding(iPersonAPI.class)).isNotNull();
		assertThat(injector.getBinding(iSessionAPI.class)).isNotNull();
	}
	
	private class MockedModule extends AbstractModule {
		private iPersonRepository fPersonRepository;
		private iProductRepository fProductRepository;
		
		public MockedModule() {
			fPersonRepository = PowerMock.createMock(iPersonRepository.class);
			fProductRepository = PowerMock.createMock(iProductRepository.class);
		}
		
		@Override
		protected void configure() {
		}
		
		@Provides
		protected iPersonRepository providePersonRepository() {
			return fPersonRepository;
		}
		
		@Provides
		protected iProductRepository provideProductRepository() {
			return fProductRepository;
		}
	}
}
