package ch.hslu.appe.fs1303.gui.models.quicksearch;

import ch.hslu.appe.fs1303.gui.GuiModule;

public abstract class QuickSearchModel<T> implements iQuickSearchModel<T> {

	public QuickSearchModel() {
		GuiModule.injector.injectMembers(this);
	}
}
