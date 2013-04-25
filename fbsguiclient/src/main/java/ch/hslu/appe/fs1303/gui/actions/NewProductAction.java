package ch.hslu.appe.fs1303.gui.actions;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import ch.hslu.appe.fs1303.gui.presenter.ProductPresenter;

public class NewProductAction extends APPEAction {
	public static final String ID= "ch.hslu.appe.fs1303.gui.actions.NewProductAction";
	private static int counter = 0;
	
	public NewProductAction() {
		setText("Produkt");
	}
	
	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public void run() {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ProductPresenter.ID, "new" + counter++ , IWorkbenchPage.VIEW_ACTIVATE);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
