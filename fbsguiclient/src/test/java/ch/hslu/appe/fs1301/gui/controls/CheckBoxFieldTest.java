package ch.hslu.appe.fs1301.gui.controls;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.fest.assertions.Assertions.assertThat;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;
import org.powermock.reflect.Whitebox;

import ch.hslu.appe.fs1303.gui.controls.APPECheckBoxField;

public class CheckBoxFieldTest extends FieldTestBase<APPECheckBoxField, Boolean>{

	@Test
	public void TestGetFieldClass() {
		RunFieldClassTest(boolean.class);
	}

	@Test
	public void TestGetValueForModel_WhenStringIsEmpty() {
		RunGetValueForModelTest("", false);
	}
	
	@Test
	public void TestGetValueForModel_WhenStringIsNull() {
		RunGetValueForModelTest(null, false);
	}
	
	@Test
	public void TestGetValueForModel_WhenStringIsFalse() {
		RunGetValueForModelTest("False", false);
	}
	
	@Test
	public void TestGetValueForModel_WhenStringIsTrue() {
		RunGetValueForModelTest("true", true);
	}
	
	@Test
	public void TestGetValueForModel_WhenStringIsNotBoolean() {
		RunGetValueForModelTest("bla", false);
	}
	
	@Test
	public void TestGetDisplayValue_WhenValueIsFalse() {
		RunGetDisplayValueTest(false, "false");
	}
	
	@Test
	public void TestGetDisplayValue_WhenValueIsTrue() {
		RunGetDisplayValueTest(true, "true");
	}
	
	@Test
	public void SetsValueToTrue_OnSetControlValue_WhenValueIsTrue(){
		final boolean ExpectedState = true;
		Button button = PowerMock.createMock(Button.class);
		Whitebox.setInternalState(fTestee, "fControl", button);
		
		button.setSelection(ExpectedState);
		PowerMock.replayAll();
		
		fTestee.setControlValue(String.valueOf(ExpectedState));
	}
	
	@Test
	public void SetsValueToFalse_OnSetControlValue_WhenValueIsFalse(){
		final boolean ExpectedState = false;
		Button button = PowerMock.createMock(Button.class);
		Whitebox.setInternalState(fTestee, "fControl", button);
		
		button.setSelection(ExpectedState);
		PowerMock.replayAll();
		
		fTestee.setControlValue(String.valueOf(ExpectedState));
	}
	
	@Test
	public void ReturnsTrue_OnGetControlValue_WhenValueIsTrue(){
		final boolean ExpectedState = false;
		Button button = PowerMock.createMock(Button.class);
		Whitebox.setInternalState(fTestee, "fControl", button);
		
		expect(button.getSelection()).andReturn(ExpectedState);
		PowerMock.replayAll();
		
		assertThat(fTestee.getControlValue()).isEqualTo(String.valueOf(ExpectedState));
	}
	
	@Test
	public void ReturnsFalse_OnGetControlValue_WhenValueIsFalse(){
		final boolean ExpectedState = false;
		Button button = PowerMock.createMock(Button.class);
		Whitebox.setInternalState(fTestee, "fControl", button);
		
		expect(button.getSelection()).andReturn(ExpectedState);
		PowerMock.replayAll();
		
		assertThat(fTestee.getControlValue()).isEqualTo(String.valueOf(ExpectedState));
	}
	
	@Test
	public void DoesNothing_OnRemoveModifyListener() {
		fTestee.removeModifyListener(null);
	}
	
	@Test
	public void AddsModifyListener() {
		Button button = PowerMock.createMock(Button.class);
		ModifyListener listenerMock = PowerMock.createMock(ModifyListener.class);
		Whitebox.setInternalState(fTestee, "fControl", button);
		Capture<Listener> cap = new Capture<Listener>();
		button.addListener(EasyMock.anyInt(), capture(cap));
		listenerMock.modifyText(null);
		PowerMock.replayAll();
		
		fTestee.addModifyListener(listenerMock);
		cap.getValue().handleEvent(null);
	}
	
	@Test
	public void CreatesSelectionButton_OnCreateControl() {
		Composite composite = PowerMock.createMock(Composite.class);
		FormToolkit formToolkit = PowerMock.createMock(FormToolkit.class);
		Button button = PowerMock.createMock(Button.class);
		
		expect(formToolkit.createButton(composite, "", SWT.CHECK)).andReturn(button);
		button.setLayoutData(isA(GridData.class));
		PowerMock.replayAll();
		fTestee.createControl(composite, formToolkit, SWT.None);
	}
}
