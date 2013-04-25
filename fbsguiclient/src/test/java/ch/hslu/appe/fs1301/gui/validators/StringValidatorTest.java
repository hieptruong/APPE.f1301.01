package ch.hslu.appe.fs1301.gui.validators;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

import ch.hslu.appe.fs1303.gui.validators.StringValidator;

public class StringValidatorTest extends ValidatorTestBase<StringValidator> {

	public StringValidatorTest(String input, boolean nullable, boolean expectedResult) {
		super(input, nullable, expectedResult);
	}

	@SuppressWarnings("rawtypes")
	@Parameters
	public static Collection getParameters() {
		Object[][] list = new Object[][] {
			{"", true, true},	
			{"", false, false},	
			{"Teststring", true, true},	
			{"Teststring", false, true},	
		};
		return Arrays.asList(list);
	}
}
