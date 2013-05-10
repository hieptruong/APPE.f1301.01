package ch.hslu.appe.fs1301.gui.controls;

import org.eclipse.swt.widgets.Text;
import org.junit.Test;

import ch.hslu.appe.fs1303.gui.controls.APPECurrencyField;
import ch.hslu.appe.fs1303.gui.controls.APPETextControl;

public class CurrencyFieldTest extends FieldTestBase<APPECurrencyField, Text, Integer> {

	protected Text doMockCreateControl() {
		return mockCreateControlForTextBox();
	}
	
	@Test
	public void TestBaseClass() {
		RunBaseClassTest(APPETextControl.class);
	}
	
	@Test
	public void TestGetFieldClass() {
		RunFieldClassTest(Integer.class);
	}
	
	@Test
	public void TestGetValueForModel_WhenStringIsEmpty() {
		RunGetValueForModelTest("", null);
	}
	
	@Test
	public void TestGetValueForModel_WhenStringIsNull() {
		RunGetValueForModelTest(null, null);
	}
	
	@Test
	public void TestGetValueForModel_WhenStringIsNumeric() {
		RunGetValueForModelTest("12.3", 1230);
	}
	
	@Test(expected = NumberFormatException.class)
	public void TestGetValueForModel_WhenStringIsNotNumeric() {
		RunGetValueForModelTest("12d3", null);
	}
	
	@Test
	public void TestGetDisplayValue_WhenValueIsNull() {
		RunGetDisplayValueTest(null, "");
	}
	
	@Test
	public void TestGetDisplayValue_WhenValueIsNumeric() {
		RunGetDisplayValueTest(1234, "12.34");
	}
}
