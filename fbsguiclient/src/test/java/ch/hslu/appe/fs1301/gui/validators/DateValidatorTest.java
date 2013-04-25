package ch.hslu.appe.fs1301.gui.validators;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

import ch.hslu.appe.fs1303.gui.validators.DateValidator;

public class DateValidatorTest extends ValidatorTestBase<DateValidator> {

	public DateValidatorTest(String input, boolean nullable, boolean expectedResult) {
		super(input, nullable, expectedResult);
	}

	@SuppressWarnings("rawtypes")
	@Parameters
	public static Collection getParameters() {
		Object[][] list = new Object[][] {
			{"", true, true},	
			{"", false, false},	
			{"Teststring", true, false},	
			{"Teststring", false, false},	
			{"12.12.2012", true, true},	
			{"12.12.2012", false, true},	
			{"12.13.2012", true, false},	
			{"12.13.2012", false, false},	
			{"32.10.2012", true, false},	
			{"32.10.2012", false, false},	
			{"30.02.2012", true, false},	
			{"30.02.2012", false, false},	
			{"5.2.12", true, true},	
			{"5.2.12", false, true},	
		};
		return Arrays.asList(list);
	}
}
