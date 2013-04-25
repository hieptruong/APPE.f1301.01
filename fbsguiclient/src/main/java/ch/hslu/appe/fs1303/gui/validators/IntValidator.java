package ch.hslu.appe.fs1303.gui.validators;


public class IntValidator implements iValidator {

	@Override
	public boolean validate(String input, boolean isNullable) {
		try {
			if (!isNullable && input.isEmpty()) return false;
			
			if (isNullable || !input.isEmpty()) {
				Integer.parseInt(input.trim());				
			} 
			
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
