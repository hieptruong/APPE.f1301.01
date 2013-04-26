package ch.hslu.appe.fs1301.gui.validators;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;

import ch.hslu.appe.fs1303.gui.validators.CurrencyValidator;

public class CurrencyValidatorTest extends ValidatorTestBase<CurrencyValidator> {

	public CurrencyValidatorTest(String input, boolean nullable, boolean expectedResult) {
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
			{"12", true, true},	
			{"12", false, true},	
			{"12.", true, true},	
			{"12.", false, true},	
			{"12.5", true, true},	
			{"12.5", false, true},	
			{"12.55", true, true},	
			{"12.55", false, true},	
			{"12.55546", true, true},	
			{"12.55546", false, true},	
		};
		return Arrays.asList(list);
	}
}
