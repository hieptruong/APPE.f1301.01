package ch.hslu.appe.fs1303.gui.actions;

import org.eclipse.jface.action.Action;

import ch.hslu.appe.fs1303.gui.GuiModule;

public abstract class APPEAction extends Action {

	public APPEAction() {
		GuiModule.injector.injectMembers(this);
	}
}
