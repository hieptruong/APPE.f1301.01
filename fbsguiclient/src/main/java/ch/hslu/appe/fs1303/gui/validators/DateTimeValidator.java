package ch.hslu.appe.fs1303.gui.validators;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeValidator implements iValidator {

	private SimpleDateFormat fDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

	@Override
	public boolean validate(String input, boolean isNullable) {
		try {
			if (isNullable || !input.isEmpty()) {				
			    fDateFormat.parse(input);  
			} 			
			return true;
		} catch (ParseException e) {
			return false;
		} 
	}

}
