package ch.hslu.appe.fs1303.gui.controls;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ch.hslu.appe.fs1303.gui.validators.IntValidator;

public class APPEIntField extends APPETextControl<Integer> {

	public APPEIntField(Composite parent, FormToolkit toolkit,
			String labelText, int style) {
		super(parent, toolkit, labelText, style);
		
		setValidator(new IntValidator());
	}

	@Override
	public Class<?> getFieldClass() {
		return Integer.class;
	}

	@Override
	public String getDisplayValue(Integer value) {
		if (value == null) return "";
		
		return String.valueOf(value);
	}

}
