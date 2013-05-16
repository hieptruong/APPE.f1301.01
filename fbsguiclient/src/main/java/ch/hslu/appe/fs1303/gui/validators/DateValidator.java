package ch.hslu.appe.fs1303.gui.validators;

import java.text.ParseException;
import java.util.Date;

import ch.hslu.appe.fs1303.gui.utils.DateUtils;

public class DateValidator implements iValidator<Date> {

	@Override
	public Date getValueFor(String value) {
		if (value == null || value.isEmpty()) 
			return null;
		
		try {
		return DateUtils.DATE_FORMAT.parse(value);
		} catch (ParseException exception) {
			throw new RuntimeException(exception);
		}
	}

	@Override
	public boolean validate(String input, boolean isNullable) {
		try {
			Date value = getValueFor(input);
			
			if (isNullable)
				return true;
			else 
				return value == null ? false : true;
		} catch (Exception e) {
			return false;
		} 
	}
}
