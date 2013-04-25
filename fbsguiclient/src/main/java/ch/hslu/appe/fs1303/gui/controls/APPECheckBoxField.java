package ch.hslu.appe.fs1303.gui.controls;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class APPECheckBoxField extends APPEControl<Boolean, Button> {

	public APPECheckBoxField(Composite parent, FormToolkit toolkit,
			String labelText, int style) {
		super(parent, toolkit, labelText, style);
	}

	@Override
	public Button createControl(Composite parent, FormToolkit toolkit, int style) {
		Button button = toolkit.createButton(parent, "", style | SWT.CHECK); 
		button.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		return button;
	}

	@Override
	public void addModifyListener(final ModifyListener listener) {
		fControl.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event event) {
				listener.modifyText(null);
			}
		});
	}

	@Override
	public void removeModifyListener(ModifyListener listener) {

	}

	@Override
	public String getControlValue() {
		return String.valueOf(fControl.getSelection());
	}

	@Override
	public Class<?> getFieldClass() {
		return boolean.class;
	}

	@Override
	public void setControlValue(String value) {
		fControl.setSelection(Boolean.parseBoolean(value));
	}

	@Override
	public Boolean getValueForModel(String value) {
		return Boolean.parseBoolean(value);
	}

	@Override
	public String getDisplayValue(Boolean value) {
		return String.valueOf(value);
	}
}
