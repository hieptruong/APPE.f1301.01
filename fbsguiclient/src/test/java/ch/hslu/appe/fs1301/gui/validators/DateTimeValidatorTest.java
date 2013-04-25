package ch.hslu.appe.fs1301.gui.validators;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

import ch.hslu.appe.fs1303.gui.validators.DateTimeValidator;

public class DateTimeValidatorTest extends ValidatorTestBase<DateTimeValidator> {

	public DateTimeValidatorTest(String input, boolean nullable, boolean expectedResult) {
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
			{"12.12.2012", true, false},	
			{"12.12.2012", false, false},	
			{"12.13.2012 00:00:00", true, false},	
			{"12.13.2012 00:00:00", false, false},	
			{"32.10.2012 00:00:00", true, false},	
			{"32.10.2012 00:00:00", false, false},	
			{"30.02.2012 00:00:00", true, false},	
			{"30.02.2012 00:00:00", false, false},	
			{"5.2.12 15:55:20", true, true},	
			{"5.2.12 15:55:20", false, true},	
			{"5.2.12 15:85:20", true, false},	
			{"5.2.12 15:85:20", false, false},	
			{"5.2.12 1:5:2", true, true},	
			{"5.2.12 1:5:2", false, true},	
		};
		return Arrays.asList(list);
	}
}
