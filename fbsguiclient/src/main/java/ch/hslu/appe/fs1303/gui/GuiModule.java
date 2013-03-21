package ch.hslu.appe.fs1303.gui;

import ch.hslu.appe.fs1303.gui.presenter.LoginPresenter;
import ch.hslu.appe.fs1303.gui.shared.iGuiClient;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;

public class GuiModule extends AbstractModule {

	public static Injector injector;
	
	@Override
	protected void configure() {
		
		bind(iGuiClient.class).to(GuiClient.class);
		bind(LoginPresenter.class);
	}

}
