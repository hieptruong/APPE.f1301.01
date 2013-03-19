package ch.hslu.appe.fs1301.data.shared;



import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class DataModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(iAPPEEntityManager.class).to(APPEEntityManager.class).in(Singleton.class);
	}

}
