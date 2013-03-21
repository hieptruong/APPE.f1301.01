package ch.hslu.appe.fs1303.gui;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.google.inject.Inject;

import ch.hslu.appe.fs1301.business.shared.iSessionAPI;
import ch.hslu.appe.fs1303.gui.dialogs.LoginDialog;
import ch.hslu.appe.fs1303.gui.shared.iGuiClient;

public final class GuiClient implements iGuiClient {
	
	private iSessionAPI fSessionAPI;

	@Inject
	public GuiClient(iSessionAPI sessionAPI) {
		fSessionAPI = sessionAPI;
	}

	public int start() {
		Display display = PlatformUI.createDisplay();
		LoginDialog loginDialog = new LoginDialog(null);
		while (loginDialog.open() != Window.CANCEL) {
			if (fSessionAPI.authenticate(loginDialog.getUser(), loginDialog.getPassword())) {
				try {
					int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
					if (returnCode == PlatformUI.RETURN_RESTART) {
						return IApplication.EXIT_RESTART;
					}
					return IApplication.EXIT_OK;
				} finally {
					display.dispose();
				}
			} else {
				loginDialog.showLoginError(true);
			}
		}		
		return IApplication.EXIT_OK;
	}
	
	public void stop() {
		if (!PlatformUI.isWorkbenchRunning())
			return;
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}
}
