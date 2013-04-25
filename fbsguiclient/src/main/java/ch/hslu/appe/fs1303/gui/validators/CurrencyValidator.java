package ch.hslu.appe.fs1303.gui.validators;

public class CurrencyValidator implements iValidator {

	@Override
	public boolean validate(String input, boolean isNullable) {
		try {
			if (!isNullable && input.isEmpty()) return false;
			
			if (!isNullable || !input.isEmpty()) {
				int index = input.lastIndexOf('.');
				if (index >= 0) {
					String firstPart = input.substring(0, index);
					firstPart = firstPart.replace(".", "");
					String secondPart = input.substring(index + 1);
					if (secondPart.length() > 2) {
						secondPart = secondPart.substring(0,2);
					}
					
					Integer.parseInt(firstPart + secondPart);
				} else {
					Integer.parseInt(input.trim());
				}				
			} 
			
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
