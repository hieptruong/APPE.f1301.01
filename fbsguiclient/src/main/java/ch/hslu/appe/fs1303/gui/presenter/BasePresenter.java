package ch.hslu.appe.fs1303.gui.presenter;

import ch.hslu.appe.fs1303.gui.GuiModule;

public class BasePresenter {

	public BasePresenter() {
		GuiModule.injector.injectMembers(this);
	}
}
