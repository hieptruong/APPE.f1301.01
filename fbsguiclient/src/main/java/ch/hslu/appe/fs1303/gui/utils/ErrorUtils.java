package ch.hslu.appe.fs1303.gui.utils;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class ErrorUtils {
	public static void handleAccessDenied(final Shell shell) {
		shell.getDisplay().asyncExec(new Runnable() {
	        public void run() {
	            MessageDialog.openError(shell, "Access Denied", "Access Denied");
	        }
	    });
	}
}
