package ch.hslu.appe.fs1301.business.shared;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ServiceModule extends AbstractModule {

	@Override
	protected void configure() {		
		bind(iSessionAPI.class).to(SessionAPI.class).in(Singleton.class);
	}

}
