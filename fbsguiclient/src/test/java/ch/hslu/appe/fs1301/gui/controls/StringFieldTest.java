package ch.hslu.appe.fs1301.gui.controls;

import org.eclipse.swt.widgets.Text;
import org.junit.Test;

import ch.hslu.appe.fs1303.gui.controls.APPEStringField;
import ch.hslu.appe.fs1303.gui.controls.APPETextControl;

public class StringFieldTest extends FieldTestBase<APPEStringField, Text, String>{

	protected Text doMockCreateControl() {
		return mockCreateControlForTextBox();
	}

	@Test
	public void TestBaseClass() {
		RunBaseClassTest(APPETextControl.class);
	}
	
	@Test
	public void TestGetFieldClass() {
		RunFieldClassTest(String.class);
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
	public void TestGetValueForModel_WhenStringIsNotEmpty() {
		RunGetValueForModelTest("False", "False");
	}
	
	@Test
	public void TestGetDisplayValue_WhenValueIsFalse() {
		RunGetDisplayValueTest("false", "false");
	}
	
	@Test
	public void TestGetDisplayValue_WhenValueIsEmpty() {
		RunGetDisplayValueTest("", "");
	}

	@Test
	public void TestGetDisplayValue_WhenValueIsNull() {
		RunGetDisplayValueTest(null, "");
	}
}
