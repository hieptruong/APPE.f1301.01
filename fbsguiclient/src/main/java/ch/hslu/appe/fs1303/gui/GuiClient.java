package ch.hslu.appe.fs1303.gui;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import ch.hslu.appe.fs1303.gui.presenter.LoginPresenter;
import ch.hslu.appe.fs1303.gui.shared.iGuiClient;

public final class GuiClient implements iGuiClient {
	
	private LoginPresenter fLoginPresenter;
	
	public GuiClient() {
		fLoginPresenter = new LoginPresenter();
	}
	
	public int start() {
		Display display = PlatformUI.createDisplay();
		
		if (fLoginPresenter.login()) {
			try {
				int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
				if (returnCode == PlatformUI.RETURN_RESTART) {
					return IApplication.EXIT_RESTART;
				}
				return IApplication.EXIT_OK;
			} finally {
				display.dispose();
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
