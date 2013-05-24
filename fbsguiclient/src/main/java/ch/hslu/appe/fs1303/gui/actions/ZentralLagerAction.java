package ch.hslu.appe.fs1303.gui.actions;

import java.util.List;

import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1303.gui.dialogs.QuickSearchDialog.iQuickSearchCallback;
import ch.hslu.appe.fs1303.gui.presenter.PersonPresenter;
import ch.hslu.appe.fs1303.gui.presenter.ZentralLagerPresenter;

public class ZentralLagerAction extends APPEAction {

	public static final String ID= "ch.hslu.appe.fs1303.gui.actions.ZentralLagerAction";
	
	public ZentralLagerAction() {
		setText("Zentrallager");
	}
	
	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public void run() {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ZentralLagerPresenter.ID, null , IWorkbenchPage.VIEW_ACTIVATE);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
