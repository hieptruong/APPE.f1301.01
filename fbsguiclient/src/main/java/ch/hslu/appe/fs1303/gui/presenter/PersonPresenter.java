package ch.hslu.appe.fs1303.gui.presenter;

import org.eclipse.swt.widgets.Composite;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1303.gui.ErrorUtils;
import ch.hslu.appe.fs1303.gui.labelprovider.PersonLabelProvider;
import ch.hslu.appe.fs1303.gui.views.iView;
import ch.hslu.appe.fs1303.gui.views.iViewListener;

import com.google.inject.Inject;

public class PersonPresenter extends BasePresenter {

	public static final String ID = "ch.hslu.appe.fs1303.gui.presenter.PersonPresenter";
	
	public interface iPersonView extends iView<DTOPerson, iPersonViewListener> {
		
	}
	
	public interface iPersonViewListener extends iViewListener {
		public void onNewOrderButtonClick();
		public void openOrder(int id);
	}
	
	@Inject
	private iPersonView fView;
	
	@Inject
	private iPersonAPI fPersonApi;

	private DTOPerson fPerson;
	
	public PersonPresenter() {
		super();
	}
	
	@Override
	public void createPartControl(Composite composite) {
		fView.createContent(composite);
		String modelId = getViewSite().getSecondaryId();
		if (modelId.startsWith("new")) {
			fPerson = new DTOPerson();
		} else {
			try {
				fPerson = fPersonApi.getCustomerById(Integer.parseInt(modelId));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return;
			} catch (AccessDeniedException e) {
				ErrorUtils.handleAccessDenied(getSite().getShell());
				return;
			}			
		}
	
		setPartName(new PersonLabelProvider().getText(fPerson));
		
		fView.bindModel(fPerson);
		fView.setActionListener(new iPersonViewListener() {
			
			@Override
			public void onSave() {
				try {
					fPerson = fPersonApi.saveCustomer(fPerson);
					fView.bindModel(fPerson);
					setPartName(new PersonLabelProvider().getText(fPerson));
				} catch (AccessDeniedException e) {
					ErrorUtils.handleAccessDenied(getSite().getShell());
				}				
			}
			
			@Override
			public void openOrder(int id) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onNewOrderButtonClick() {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void setFocus() {
		
	}

}
