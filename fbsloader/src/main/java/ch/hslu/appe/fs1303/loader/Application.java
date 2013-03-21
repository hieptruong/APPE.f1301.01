package ch.hslu.appe.fs1303.loader;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import ch.hslu.appe.fs1301.business.ServiceModule;
import ch.hslu.appe.fs1301.data.DataModule;
import ch.hslu.appe.fs1303.gui.GuiModule;
import ch.hslu.appe.fs1303.gui.shared.iGuiClient;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	private iGuiClient fGuiClient;

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	public Object start(IApplicationContext context) {		
		Injector injector = Guice.createInjector(new GuiModule(), new ServiceModule(), new DataModule());
		
		fGuiClient = injector.getInstance(iGuiClient.class);
		return fGuiClient.start();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		fGuiClient.stop();
	}
}
