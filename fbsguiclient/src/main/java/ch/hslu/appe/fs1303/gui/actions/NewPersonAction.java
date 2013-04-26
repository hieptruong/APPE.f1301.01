package ch.hslu.appe.fs1303.gui.actions;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import ch.hslu.appe.fs1303.gui.presenter.PersonPresenter;

public class NewPersonAction extends APPEAction {

	public static final String ID= "ch.hslu.appe.fs1303.gui.actions.NewPersonAction";
	private static int counter = 0;
	
	public NewPersonAction() {
		setText("Person");
	}
	
	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public void run() {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(PersonPresenter.ID, "new" + counter++ , IWorkbenchPage.VIEW_ACTIVATE);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
