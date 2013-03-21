package ch.hslu.appe.fs1303.gui;

import ch.hslu.appe.fs1303.gui.shared.iGuiClient;

import com.google.inject.AbstractModule;

public class GuiModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(iGuiClient.class).to(GuiClient.class);
	}

}
