package ch.hslu.appe.fs1301.gui.controls;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.widgets.Text;
import org.junit.Test;

import ch.hslu.appe.fs1303.gui.controls.APPEDateField;
import ch.hslu.appe.fs1303.gui.controls.APPETextControl;

public class DateFieldTest extends FieldTestBase<APPEDateField, Text, Date> {

	protected Text doMockCreateControl() {
		return mockCreateControlForTextBox();
	}
	
	@Test
	public void TestBaseClass() {
		RunBaseClassTest(APPETextControl.class);
	}
	
	@Test
	public void TestGetFieldClass() {
		RunFieldClassTest(Date.class);
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
	public void TestGetValueForModel_WhenStringIsCorrect() {
		RunGetValueForModelTest("5.2.2012", getDateFor(2012, 2, 5));
	}
	
	@Test
	public void TestGetDisplayValue_WhenValueIsNull() {
		RunGetDisplayValueTest(null, "");
	}
	
	@Test
	public void TestGetDisplayValue() {
		RunGetDisplayValueTest(getDateFor(2012,3,5), "05.03.2012");
	}
	
	private Date getDateFor(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.set(year, month-1, day, 0, 0, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
