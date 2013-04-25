package ch.hslu.appe.fs1303.gui.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeValidator implements iValidator {

	private static final SimpleDateFormat fDateFormat;

	static {
		fDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
		fDateFormat.setLenient(false);
	}
	
	@Override
	public boolean validate(String input, boolean isNullable) {
		try {
			if (!isNullable && input.isEmpty()) return false;
			
			if (isNullable && input.isEmpty()) return true;
			
			if (isNullable || !input.isEmpty()) {				
			    fDateFormat.parse(input);  
			} 			
			return true;
		} catch (ParseException e) {
			return false;
		} 
	}

}
