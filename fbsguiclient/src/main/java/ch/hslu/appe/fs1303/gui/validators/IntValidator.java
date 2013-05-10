package ch.hslu.appe.fs1303.gui.validators;


public class IntValidator implements iValidator<Integer> {

	public Integer getValueFor(String value) {
		if (value == null || value.isEmpty()) 
			return null;
		
		return Integer.parseInt(value);
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
