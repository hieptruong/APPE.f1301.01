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
		return int.class;
	}

	@Override
	public Integer getValueForModel(String value) {
		if (value == null || value.isEmpty()) return null;
				
		int index = value.lastIndexOf('.');
		if (index >= 0) {
			String firstPart = value.substring(0, index);
			firstPart = firstPart.replace(".", "");
			String secondPart = value.substring(index + 1);
			if (secondPart.length() > 2) {
				secondPart = secondPart.substring(0,2);
			} else if (secondPart.length() == 1) {
				secondPart += "0";
			} else if (secondPart.length() == 0) {
				secondPart += "00";
			}
			
			return Integer.parseInt(firstPart + secondPart);
		} else {
			return Integer.parseInt(value.trim()) * 100;
		}				
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
