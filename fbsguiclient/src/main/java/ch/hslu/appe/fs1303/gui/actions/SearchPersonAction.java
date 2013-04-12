package ch.hslu.appe.fs1303.gui.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1303.gui.dialogs.PersonSearchDialog;
import ch.hslu.appe.fs1303.gui.dialogs.PersonSearchDialog.iPersonSearchCallback;

import com.google.inject.Inject;

public class SearchPersonAction extends APPEAction implements iPersonSearchCallback {

	public static final String ID= "ch.hslu.appe.fs1303.gui.actions.SearchPersonAction";
	
	private PersonSearchDialog fPersonSearchDialog;
	private List<DTOPerson> fLastSearchResult;
	
	@Inject
	private iPersonAPI personAPI;

	public SearchPersonAction() {
		setText("Person suchen");
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		fPersonSearchDialog = new PersonSearchDialog(shell, true, this);
		fLastSearchResult = new ArrayList<DTOPerson>();
	}
	
	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public void run() {
		if (fPersonSearchDialog.open() == Window.OK) {
			// Open Person
		}
	}

	@Override
	public List<DTOPerson> personsForSearchString(String input) {
		fLastSearchResult = personAPI.getCustomersByName(input);
		return fLastSearchResult;
	}

	@Override
	public boolean matchItem(DTOPerson item) {
		return fLastSearchResult.contains(item);
	}
}
