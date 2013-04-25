package ch.hslu.appe.fs1303.gui.actions;

import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1303.gui.ErrorUtils;
import ch.hslu.appe.fs1303.gui.dialogs.PersonSearchDialog;
import ch.hslu.appe.fs1303.gui.dialogs.QuickSearchDialog.iQuickSearchCallback;
import ch.hslu.appe.fs1303.gui.presenter.PersonPresenter;

import com.google.inject.Inject;

public class SearchPersonAction extends APPEAction implements iQuickSearchCallback<DTOPerson> {

	public static final String ID= "ch.hslu.appe.fs1303.gui.actions.SearchPersonAction";
	
	private PersonSearchDialog fPersonSearchDialog;	
	
	@Inject
	private iPersonAPI personAPI;

	public SearchPersonAction() {
		setText("Person suchen");
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		fPersonSearchDialog = new PersonSearchDialog(shell, true, this);		
	}
	
	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public void run() {
		if (fPersonSearchDialog.open() == Window.OK) {
			for (Object person : fPersonSearchDialog.getResult()) {
				if (person instanceof DTOPerson) {
					try {						
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(PersonPresenter.ID, String.valueOf(((DTOPerson)person).getId()), IWorkbenchPage.VIEW_ACTIVATE);
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public List<DTOPerson> resultForSearchString(String input) {
		try {
			return personAPI.getCustomersByName(input);
		} catch (AccessDeniedException e) {
			ErrorUtils.handleAccessDenied(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
			return null;
		}		
	}
}
