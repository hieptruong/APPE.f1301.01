package ch.hslu.appe.fs1303.gui.validators;

public interface iValidator<T> {
	/**
	 * Gets the value for the provided string.
	 * Throws all occurring exceptions.
	 * @param value The value to parse.
	 * @return The parsed value.
	 */
	public T getValueFor(String value);
	/**
	 * Validates the input.
	 * @param input The input string.
	 * @param isNullable If it is nullable.
	 * @return True if it is valid, false otherwise.
	 */
	public boolean validate(String input, boolean isNullable);
}
