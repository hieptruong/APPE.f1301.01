package ch.hslu.appe.fs1303.gui.validators;

public class StringValidator implements iValidator<String> {

	@Override
	public String getValueFor(String value) {
		if (value == null) 
			return null;
		value = value.trim();
		if (value.isEmpty())
			return null;
		return value;
	}

	@Override
	public boolean validate(String input, boolean isNullable) {
		String value = getValueFor(input);
		
		if (isNullable)
			return true;
		else 
			return value == null ? false : true;
	}
}
