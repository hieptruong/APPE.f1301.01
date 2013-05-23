package ch.hslu.appe.fs1301.data;

import ch.hslu.appe.fs1301.data.shared.*;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * @author Thomas Bomatter
 * Guice binding modul for injections
 */
public class DataModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(APPEEntityManager.class).in(Singleton.class);
		bind(iAPPEEntityManager.class).to(APPEEntityManager.class);
		bind(iTransaction.class).to(APPEEntityManager.class);
		
		bind(iPersonRepository.class).to(PersonRepository.class).in(Singleton.class);
		bind(iProductRepository.class).to(ProductRepository.class).in(Singleton.class);
		bind(iOrderRepository.class).to(OrderRepository.class).in(Singleton.class);
		bind(iOrderPositionRepository.class).to(OrderPositionRepository.class).in(Singleton.class);
		bind(iStockRepository.class).to(StockRepository.class).in(Singleton.class);
	}
}
