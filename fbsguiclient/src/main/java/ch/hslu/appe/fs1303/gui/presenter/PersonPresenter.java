package ch.hslu.appe.fs1303.gui.presenter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.iOrderAPI;
import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1303.gui.labelprovider.PersonLabelProvider;
import ch.hslu.appe.fs1303.gui.models.PersonEditorModel;
import ch.hslu.appe.fs1303.gui.utils.ArrayUtils;
import ch.hslu.appe.fs1303.gui.utils.ErrorUtils;
import ch.hslu.appe.fs1303.gui.views.iView;
import ch.hslu.appe.fs1303.gui.views.iViewListener;

import com.google.inject.Inject;

public class PersonPresenter extends BasePresenter {

	public static final String ID = "ch.hslu.appe.fs1303.gui.presenter.PersonPresenter";
	
	public interface iPersonView extends iView<PersonEditorModel, iPersonViewListener> {
		
	}
	
	public interface iPersonViewListener extends iViewListener {
		public void onNewOrderButtonClick();
		public void openOrder(int id);
	}
	
	@Inject
	private iPersonView fView;
	
	@Inject
	private iPersonAPI fPersonApi;
	
	@Inject
	private iOrderAPI fOrderApi;

	private PersonEditorModel fPersonEditorModel;
	
	public PersonPresenter() {
		super();
	}
	
	@Override
	public void createPartControl(Composite composite) {
		fView.createContent(composite);
		String modelId = getViewSite().getSecondaryId();
		if (modelId.startsWith("new")) {
			DTOPerson person = new DTOPerson();
			fPersonEditorModel = new PersonEditorModel(person, new ArrayList<DTOBestellung>(), new ArrayList<DTOBestellung>());
		} else {
			try {
				DTOPerson person = fPersonApi.getCustomerById(Integer.parseInt(modelId));
				List<DTOBestellung> orders = fOrderApi.getOrders(ArrayUtils.convertToIntArray(person.getBestellungs1()));
				List<DTOBestellung> createdOrders = fOrderApi.getOrders(ArrayUtils.convertToIntArray(person.getBestellungs2()));
				fPersonEditorModel = new PersonEditorModel(person, orders, createdOrders);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return;
			} catch (AccessDeniedException e) {
				ErrorUtils.handleAccessDenied(getSite().getShell());
				return;
			}			
		}
	
		setPartName(new PersonLabelProvider().getText(fPersonEditorModel.getPerson()));
		
		fView.bindModel(fPersonEditorModel);
		fView.setActionListener(new iPersonViewListener() {
			
			@Override
			public void onSave() {
				try {
					DTOPerson person = fPersonApi.saveCustomer(fPersonEditorModel.getPerson());
					fPersonEditorModel.setPerson(person);
					fView.bindModel(fPersonEditorModel);
					setPartName(new PersonLabelProvider().getText(person));
				} catch (AccessDeniedException e) {
					ErrorUtils.handleAccessDenied(getSite().getShell());
				}				
			}
			
			@Override
			public void openOrder(int id) {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(OrderPresenter.ID, String.valueOf(id) , IWorkbenchPage.VIEW_ACTIVATE);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void onNewOrderButtonClick() {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(OrderPresenter.ID, "new" + fPersonEditorModel.getPerson().getId(), IWorkbenchPage.VIEW_ACTIVATE);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void setFocus() {
		
	}

}
