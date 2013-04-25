package ch.hslu.appe.fs1303.gui.controls;

import java.text.SimpleDateFormat;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ch.hslu.appe.fs1303.gui.validators.DateTimeValidator;

public class APPEDateTimeField extends APPEDateField {

	public APPEDateTimeField(Composite parent, FormToolkit toolkit,
			String labelText, int style) {
		super(parent, toolkit, labelText, style);

		fDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		setValidator(new DateTimeValidator());
	}

}
