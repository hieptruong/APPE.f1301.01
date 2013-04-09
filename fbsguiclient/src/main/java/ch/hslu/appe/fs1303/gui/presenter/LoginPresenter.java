package ch.hslu.appe.fs1303.gui.presenter;

import org.eclipse.jface.window.Window;

import com.google.inject.Inject;

import ch.hslu.appe.fs1301.business.shared.iSessionAPI;
import ch.hslu.appe.fs1303.gui.dialogs.LoginDialog;

public class LoginPresenter extends BasePresenter {

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
}
