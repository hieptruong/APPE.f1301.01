package ch.hslu.appe.fs1303.gui.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


public class LoginDialog extends Dialog {

	private Text userText;
	private Text passwordText;
	private String user = "";
	private String password = "";
	private boolean showLoginError = false;
	private Label loginErrorLabel;
	
	public LoginDialog(Shell parentShell) {
		super(parentShell);
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("APPE RCP Login");
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
	    GridLayout gl_container = new GridLayout(2, false);
	    gl_container.marginRight = 5;
	    gl_container.marginLeft = 10;
	    container.setLayout(gl_container);

	    Label lblUser = new Label(container, SWT.NONE);
	    lblUser.setText("User:");

	    userText = new Text(container, SWT.BORDER);
	    userText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	    userText.setText(user);

	    Label lblNewLabel = new Label(container, SWT.NONE);
	    GridData gd_lblNewLabel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
	    gd_lblNewLabel.horizontalIndent = 1;
	    lblNewLabel.setLayoutData(gd_lblNewLabel);
	    lblNewLabel.setText("Password:");

	    passwordText = new Text(container, SWT.BORDER | SWT.PASSWORD);
	    passwordText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	    passwordText.setText(password);
	    
	    loginErrorLabel = new Label(container, SWT.NONE);
	    loginErrorLabel.setText("Invalid User / Password");
	    loginErrorLabel.setForeground(new Color(null, 255, 0, 0)); // Red
	    loginErrorLabel.setVisible(showLoginError);
	    
	    return container;
	}
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected Point getInitialSize() {
		return new Point(450, showLoginError ? 200 : 180);
	}

	@Override
	protected void okPressed() {
		user = userText.getText();
	    password = passwordText.getText();

	    super.okPressed();
	}

	public String getUser() {
	    return user;
	}

	public void showLoginError(boolean visible) {
		this.showLoginError = visible;
	}
	
	public void setUser(String user) {
	    this.user = user;
	}

	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}
}
