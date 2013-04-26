package ch.hslu.appe.fs1303.gui.validators;

import java.text.ParseException;

import ch.hslu.appe.fs1303.gui.utils.DateUtils;

public class DateTimeValidator implements iValidator {

	@Override
	public boolean validate(String input, boolean isNullable) {
		try {
			if (!isNullable && input.isEmpty()) return false;
			
			if (isNullable && input.isEmpty()) return true;
			
		    DateUtils.DATE_TIME_FORMAT.parse(input);  
			return true;
		} catch (ParseException e) {
			return false;
		} 
	}
}
