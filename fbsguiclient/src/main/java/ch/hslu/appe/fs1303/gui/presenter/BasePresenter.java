package ch.hslu.appe.fs1303.gui.presenter;

import org.eclipse.ui.part.ViewPart;

import ch.hslu.appe.fs1303.gui.GuiModule;

public abstract class BasePresenter extends ViewPart {

	public BasePresenter() {
		GuiModule.injector.injectMembers(this);
	}
}
