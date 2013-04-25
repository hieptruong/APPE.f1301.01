package ch.hslu.appe.fs1303.gui.controls;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ch.hslu.appe.fs1303.gui.validators.DateValidator;

public class APPEDateField extends APPETextControl<Date>{

	protected SimpleDateFormat fDateFormat;

	public APPEDateField(Composite parent, FormToolkit toolkit,
			String labelText, int style) {
		super(parent, toolkit, labelText, style);
		
		fDateFormat = new SimpleDateFormat("dd.MM.yyyy");
		setValidator(new DateValidator());
	}

	@Override
	public Class<?> getFieldClass() {
		return Date.class;
	}

	@Override
	public Date getValueForModel(String value) {
		if (value == null) return null;
		
		try {
			return fDateFormat.parse(value);
		} catch (ParseException e) {
			return null;
		}
	}

	@Override
	public String getDisplayValue(Date value) {
		if (value == null) return "";
		
		return fDateFormat.format(value);
	}

}
