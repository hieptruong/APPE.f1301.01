package ch.hslu.appe.fs1303.gui.presenter;

import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1303.gui.labelprovider.PersonLabelProvider;
import ch.hslu.appe.fs1303.gui.views.iView;

public class PersonPresenter extends BasePresenter {

	public static final String ID = "ch.hslu.appe.fs1303.gui.presenter.PersonPresenter";
	
	public interface iPersonView extends iView {
		public void addActionListener(iPersonViewActions actionListener);
		public void updateFromDTO(DTOPerson person);
	}
	
	public interface iPersonViewActions {
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
		
		fPerson = fPersonApi.getCustomerById(Integer.parseInt(getViewSite().getSecondaryId()));
		setPartName(new PersonLabelProvider().getText(fPerson));
		fView.updateFromDTO(fPerson);
		fView.addActionListener(new iPersonViewActions() {
			@Override
			public void openOrder(int id) {

			}
			
			@Override
			public void onNewOrderButtonClick() {

			}
		});
	}
	
	@Override
	public void setFocus() {
		
	}

}
