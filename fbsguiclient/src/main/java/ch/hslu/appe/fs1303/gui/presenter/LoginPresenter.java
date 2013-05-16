package ch.hslu.appe.fs1303.gui.presenter;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;

import ch.hslu.appe.fs1301.business.shared.iSessionAPI;
import ch.hslu.appe.fs1303.gui.dialogs.LoginDialog;

import com.google.inject.Inject;

public class LoginPresenter extends BasePresenter<String> {

	private LoginDialog fLoginDialog;
	
	@Inject
	private iSessionAPI fSessionAPI;
	
	public LoginPresenter() {
		fLoginDialog = new LoginDialog(null);
	}
	
	public boolean login() {
		while (fLoginDialog.open() != Window.CANCEL) {
			if (fSessionAPI.authenticate(fLoginDialog.getUser(), fLoginDialog.getPassword())) {
				return true;
			} else {
				fLoginDialog.showLoginError(true);
			}
		}	
		return false;
	}

	@Override
	public void setFocus() {
		
	}

	@Override
	public String loadModel() {
		return null;
	}

	@Override
	public void createControls(Composite composite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bindModel(String model) {
		// TODO Auto-generated method stub
		
	}
}
