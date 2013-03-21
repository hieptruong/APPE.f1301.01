package ch.hslu.appe.fs1303.loader;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import ch.hslu.appe.fs1301.business.ServiceModule;
import ch.hslu.appe.fs1301.business.shared.iSessionAPI;
import ch.hslu.appe.fs1301.data.DataModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public Object start(IApplicationContext context) {		
		Display display = PlatformUI.createDisplay();
		
		Injector injector = Guice.createInjector(new ServiceModule(), new DataModule());
		iSessionAPI sessionAPI = injector.getInstance(iSessionAPI.class);
		
		ch.hslu.appe.fs1303.gui.dialogs.LoginDialog loginDialog = new ch.hslu.appe.fs1303.gui.dialogs.LoginDialog(null);
		while (loginDialog.open() != Window.CANCEL) {
			if (sessionAPI.authenticate(loginDialog.getUser(), loginDialog.getPassword())) {
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

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
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
