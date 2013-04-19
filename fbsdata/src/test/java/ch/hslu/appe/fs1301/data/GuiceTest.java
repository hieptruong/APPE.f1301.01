package ch.hslu.appe.fs1301.data;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.iOrderPositionRepository;
import ch.hslu.appe.fs1301.data.shared.iOrderRepository;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.iProductRepository;
import ch.hslu.appe.fs1301.data.shared.iRepository;
import ch.hslu.appe.fs1301.data.shared.iTransaction;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Thomas Bomatter
 * Guice injection test
 */
public class GuiceTest extends BaseTestClass {

	private Injector fInjector;

	@Before
	public void setUp() {
		fInjector = Guice.createInjector(new DataModule());
	}
	
	@Test
	public void isTheSameReference_ForAPPEEntityManagerAndTransaction() {
		iAPPEEntityManager entityManager = fInjector.getInstance(iAPPEEntityManager.class);
		iTransaction transaction = fInjector.getInstance(iTransaction.class);
		
		assertNotNull(entityManager);
		assertNotNull(transaction);
		
		assertTrue(entityManager == transaction);
	}
	
	@Test
	public void allRepositoriesCanBeConstructed() {
		List<Class<? extends iRepository<?>>> repos = new ArrayList<Class<? extends iRepository<?>>>();
		repos.add(iOrderPositionRepository.class);
		repos.add(iOrderRepository.class);
		repos.add(iPersonRepository.class);
		repos.add(iProductRepository.class);
		
		for(Class<? extends iRepository<?>> clazz : repos) {
			iRepository<?> instance = fInjector.getInstance(clazz);
			assertNotNull(instance);
		}
	}
}
