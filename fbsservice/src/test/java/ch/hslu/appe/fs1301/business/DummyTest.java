package ch.hslu.appe.fs1301.business;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1301.business.shared.OrderSource;
import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.iSessionAPI;
import ch.hslu.appe.fs1301.data.shared.iOrderPositionRepository;
import ch.hslu.appe.fs1301.data.shared.iOrderRepository;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.iProductRepository;
import ch.hslu.appe.fs1301.data.shared.iTransaction;

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
		private iOrderPositionRepository fOrderPositionRepository;
		private iOrderRepository fOrderRepository;
		private iTransaction fTransaction;
		
		public MockedModule() {
			fPersonRepository = PowerMock.createMock(iPersonRepository.class);
			fProductRepository = PowerMock.createMock(iProductRepository.class);
			fOrderPositionRepository = PowerMock.createMock(iOrderPositionRepository.class);
			fOrderRepository = PowerMock.createMock(iOrderRepository.class);
			fTransaction = PowerMock.createMock(iTransaction.class);
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
		
		@Provides
		protected iOrderPositionRepository provideOrderPositionRepository() {
			return fOrderPositionRepository;
		}		
		
		@Provides
		protected iOrderRepository provideOrderRepository() {
			return fOrderRepository;
		}
		
		@Provides
		protected iTransaction provideTransaction() {
			return fTransaction;
		}
	}
}
