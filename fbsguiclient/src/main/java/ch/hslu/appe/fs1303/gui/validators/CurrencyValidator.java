package ch.hslu.appe.fs1303.gui.validators;

public class CurrencyValidator implements iValidator<Integer> {

	@Override
	public Integer getValueFor(String value) {
		if (value == null || value.isEmpty()) 
			return null;
		
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
	public boolean validate(String input, boolean isNullable) {
		try {
			Integer value = getValueFor(input);
			
			if (isNullable)
				return true;
			else 
				return value == null ? false : true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
