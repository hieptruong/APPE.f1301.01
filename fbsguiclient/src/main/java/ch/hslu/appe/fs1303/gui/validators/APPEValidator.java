package ch.hslu.appe.fs1303.gui.validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;


public class APPEValidator {

	public static boolean valideInt(Text text) {
		return valideInt(text, true);
	}
	
	public static boolean valideInt(Text text, boolean allowNull) {
		try {
			if (!allowNull || !text.getText().isEmpty()) {
				Integer.parseInt(text.getText().trim());				
			} 
			
			text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
			return true;
		} catch (NumberFormatException e) {
			text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
			return false;
		}
	}
	
	public static boolean valideDate(Text text) {
		return valideDate(text, true);
	}
	public static boolean valideDate(Text text, boolean allowNull) {
		try {
			if (!allowNull || !text.getText().isEmpty()) {				
			    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			    df.parse(text.getText());  
			} 
			
			text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
			return true;
		} catch (ParseException e) {
			text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
			return false;
		} 
	}
	
	public static boolean valideDateTime(Text text) {
		return valideDateTime(text, true);
	}
	public static boolean valideDateTime(Text text, boolean allowNull) {
		try {
			if (!allowNull || !text.getText().isEmpty()) {				
			    DateFormat df = new SimpleDateFormat("dd.MM.yyyy kk:mm:ss");
			    df.parse(text.getText());  
			} 
			
			text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
			return true;
		} catch (ParseException e) {
			text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
			return false;
		} 
	}
	
	public static boolean validateNotNull(Text text) {
		if (text.getText().trim().isEmpty()) {
			text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
			return false;
		} else {
			text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
			return true;
		}
	}
}
