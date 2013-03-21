package ch.hslu.appe.fs1301.data;

import org.junit.Test;
import static org.junit.Assert.*;
import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author Thomas Bomatter
 * Guice injection test
 */
public class GuiceTest extends BaseTestClass {

	@Test
	public void testDataModuleInject() {
		Injector injector = Guice.createInjector(new DataModule());
		iAPPEEntityManager entityManager = injector.getInstance(iAPPEEntityManager.class);
		assertNotNull(entityManager);
		assertEquals(APPEEntityManager.class, entityManager.getClass());
	}
}
