package ch.hslu.appe.fs1303.gui.controls;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1303.gui.controls.APPECheckBoxField;
import ch.hslu.appe.fs1303.gui.controls.APPEComboBoxField;
import ch.hslu.appe.fs1303.gui.datasource.ComboDataSource;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ComboBoxCreator.class)
public class ComboBoxFieldTest extends FieldTestBase<APPEComboBoxField, Combo, Integer>{

	protected Combo doMockCreateControl() throws Exception {
		PowerMock.mockStatic(ComboBoxCreator.class);
		Combo combo = PowerMock.createMock(Combo.class);
		expect(ComboBoxCreator.createCombo(fParent, fStyle)).andReturn(combo);
		combo.setLayoutData(isA(GridData.class));
		fToolkit.adapt(combo);
		combo.addModifyListener(isA(ModifyListener.class));
		return combo;
	}
	
	private void setUpEmptyDataSource() {
		List<ComboDataSource> dataSource = new ArrayList<ComboDataSource>();
		fTestee.setDataSource(dataSource);
	}
	
	
	@Test
	public void TestGetFieldClass() {
		setUpEmptyDataSource();
		RunFieldClassTest(Integer.class);
	}

	@Test
	public void RemovesModifyListener() {
		PowerMock.reset(fControl);
		ModifyListener listener = PowerMock.createMock(ModifyListener.class);
		fControl.removeModifyListener(listener);
		PowerMock.replayAll();
		
		fTestee.removeModifyListener(listener);
	}
	
	@Test
	public void SetsDataSourceIntoControl() {
		List<ComboDataSource> dataSource = ComboDataSource.getDataSourceForClass(UserRole.class);
		
		PowerMock.reset(fControl);
		fControl.add(isA(String.class));
		EasyMock.expectLastCall().anyTimes();
		PowerMock.replayAll();
		
		fTestee.setDataSource(dataSource);
		
		assertThat(fTestee.getDataSource()).isSameAs(dataSource);
	}
	
	@Test
	public void TestGetValueForModel_WhenStringIsEmpty() {
		setUpEmptyDataSource();
		RunGetValueForModelTest("", null);
	}
	
	@Test
	public void TestGetValueForModel_WhenStringIsUnknown() {
		SetsDataSourceIntoControl();
		RunGetValueForModelTest(null, null);
	}
	
	@Test
	public void TestGetValueForModel_WhenStringIsKnown() {
		RunGetValueForModelTest("NONE", null);
	}
	
	@Test
	public void TestGetValueForModel_WhenStringIsTrue() {
		RunGetValueForModelTest("true", null);
	}
	
	@Test
	public void TestGetValueForModel_WhenStringIsNotBoolean() {
		RunGetValueForModelTest("bla", null);
	}
	
	@Test
	public void TestGetDisplayValue_WhenValueIsFalse() {
		RunGetDisplayValueTest(null, "false");
	}
	
	@Test
	public void TestGetDisplayValue_WhenValueIsTrue() {
		RunGetDisplayValueTest(null, "true");
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
