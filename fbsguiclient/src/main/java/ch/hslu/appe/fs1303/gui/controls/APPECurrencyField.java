package ch.hslu.appe.fs1303.gui.controls;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ch.hslu.appe.fs1303.gui.validators.CurrencyValidator;

public class APPECurrencyField extends APPETextControl<Integer> {

	public APPECurrencyField(Composite parent, FormToolkit toolkit,
			String labelText, int style) {
		super(parent, toolkit, labelText, style);
		
		setValidator(new CurrencyValidator());
	}

	@Override
	public Class<?> getFieldClass() {
		return Integer.class;
	}

	@Override
	public String getDisplayValue(Integer value) {
		if (value == null) return "";
		
		String sValue = String.valueOf(value);
		if (sValue.length() > 2) {
			sValue = sValue.substring(0, sValue.length()-2) + "." + sValue.substring(sValue.length()-2);
		} else {
			sValue = String.format("0.%02d", value);
		}
		
		return sValue;
	}

}
