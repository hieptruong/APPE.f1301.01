package ch.hslu.appe.fs1303.gui.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public abstract class APPETextControl<T> extends APPEControl<T, Text> {

	public APPETextControl(Composite parent, FormToolkit toolkit,
			String labelText, int style) {
		super(parent, toolkit, labelText, style);
	}

	@Override
	public Text createControl(Composite parent, FormToolkit toolkit, int style) {
		 Text text = toolkit.createText(parent, null, style);
		 text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		 
		 return text;
	}
	
	@Override
	public void addModifyListener(ModifyListener listener) {
		fControl.addModifyListener(listener);
	}
	
	@Override
	public void removeModifyListener(ModifyListener listener) {
		fControl.removeModifyListener(listener);
	}
	
	@Override
	public String getControlValue() {
		return fControl.getText();
	}
	
	@Override
	public void setControlValue(String value) {
		fControl.setText(value);
	}
}
