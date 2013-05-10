package ch.hslu.appe.fs1303.gui.controls;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ch.hslu.appe.fs1303.gui.utils.DateUtils;
import ch.hslu.appe.fs1303.gui.validators.DateValidator;

public class APPEDateField extends APPETextControl<Date>{

	protected SimpleDateFormat fDateFormat;

	public APPEDateField(Composite parent, FormToolkit toolkit,
			String labelText, int style) {
		super(parent, toolkit, labelText, style);
		fDateFormat = DateUtils.DATE_FORMAT;
		setValidator(new DateValidator());
	}

	@Override
	public Class<?> getFieldClass() {
		return Date.class;
	}

	@Override
	public String getDisplayValue(Date value) {
		if (value == null) return "";
		
		return fDateFormat.format(value);
	}

}
