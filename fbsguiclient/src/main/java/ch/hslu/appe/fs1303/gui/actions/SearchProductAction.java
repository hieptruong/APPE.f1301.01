package ch.hslu.appe.fs1303.gui.actions;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.iProductAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1303.gui.ErrorUtils;
import ch.hslu.appe.fs1303.gui.dialogs.ProductSearchDialog;
import ch.hslu.appe.fs1303.gui.dialogs.QuickSearchDialog.iQuickSearchCallback;
import ch.hslu.appe.fs1303.gui.presenter.ProductPresenter;

import com.google.inject.Inject;

public class SearchProductAction extends APPEAction implements iQuickSearchCallback<DTOProdukt> {

	public static final String ID= "ch.hslu.appe.fs1303.gui.actions.SearchProductAction";
	
	private ProductSearchDialog fProductSearchDialog;
	
	@Inject
	private iProductAPI produktAPI;

	private List<DTOProdukt> fAllProducts;

	public SearchProductAction() {
		setText("Produkt suchen");
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		fProductSearchDialog = new ProductSearchDialog(shell, true, this);	
		try {
			fAllProducts = produktAPI.getAllProducts();
		} catch (AccessDeniedException e) {
			ErrorUtils.handleAccessDenied(shell);
		}
	}
	
	@Override
	public String getId() {
		return ID;
	}
	
	@Override
	public void run() {
		if (fProductSearchDialog.open() == Window.OK) {
			for (Object produkt : fProductSearchDialog.getResult()) {
				if (produkt instanceof DTOProdukt) {
					try {						
						PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(ProductPresenter.ID, String.valueOf(((DTOProdukt)produkt).getId()), IWorkbenchPage.VIEW_ACTIVATE);
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public List<DTOProdukt> resultForSearchString(String input) {
		 List<DTOProdukt> results = new ArrayList<DTOProdukt>();
		 for (DTOProdukt dtoProdukt : fAllProducts) {
			 if (dtoProdukt.getBezeichnung().contains(input))
				 results.add(dtoProdukt);			
		 }
		 return results;
	}
}
