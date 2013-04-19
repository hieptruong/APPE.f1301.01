package ch.hslu.appe.fs1303.gui.presenter;

import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1303.gui.labelprovider.PersonLabelProvider;
import ch.hslu.appe.fs1303.gui.views.iView;
import ch.hslu.appe.fs1303.gui.views.iViewListener;

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
		
		fPerson = fPersonApi.getCustomerById(Integer.parseInt(getViewSite().getSecondaryId()));
		setPartName(new PersonLabelProvider().getText(fPerson));
		fView.bindModel(fPerson);
		fView.setActionListener(new iPersonViewListener() {
			
			@Override
			public void onSave() {
				// TODO Auto-generated method stub
				
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
