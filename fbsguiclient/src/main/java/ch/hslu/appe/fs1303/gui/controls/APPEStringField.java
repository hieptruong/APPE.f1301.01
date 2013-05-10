package ch.hslu.appe.fs1303.gui.controls;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ch.hslu.appe.fs1303.gui.validators.StringValidator;

public class APPEStringField extends APPETextControl<String> {
	
	public APPEStringField(Composite parent, FormToolkit toolkit,
			String labelText, int style) {
		super(parent, toolkit, labelText, style);
		
		setValidator(new StringValidator());
	}

	@Override
	public Class<?> getFieldClass() {
		return String.class;
	}

	@Override
	public String getDisplayValue(String value) {
		if (value == null) return "";
		
		return value;
	}
}
