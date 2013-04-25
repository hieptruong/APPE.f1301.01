package ch.hslu.appe.fs1303.gui.validators;

public class StringValidator implements iValidator {

	@Override
	public boolean validate(String input, boolean isNullable) {
		if (input.trim().isEmpty() && !isNullable) {
			return false;
		} else {
			return true;
		}
	}
}
