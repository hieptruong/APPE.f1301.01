package ch.hslu.appe.fs1303.gui.presenter;

import org.eclipse.swt.widgets.Composite;

import ch.hslu.appe.fs1303.gui.views.PersonSearchView;
import ch.hslu.appe.fs1303.gui.views.iView;

public class PersonSearchPresenter extends BasePresenter {

	public interface Display extends iView {
		
	}
	
	public static final String ID= "ch.hslu.appe.fs1303.gui.presenter.PersonSearchPresenter";
	
	private Display fView; 
	

	@Override
	public void createPartControl(Composite parent) {
		fView = new PersonSearchView();
		fView.createContent(parent);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
