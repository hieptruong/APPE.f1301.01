package ch.hslu.appe.fs1303.gui.controls;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import ch.hslu.appe.fs1301.business.shared.UserRole;
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
		SetsDataSourceIntoControl();
		RunGetValueForModelTest("NONE", UserRole.NONE);
	}
	
	@Test
	public void TestGetDisplayValue_WhenValueIsNull() {
		SetsDataSourceIntoControl();
		RunGetDisplayValueTest(null, null);
	}
	
	@Test
	public void TestGetDisplayValue_WhenValueIsKnown() {
		SetsDataSourceIntoControl();
		RunGetDisplayValueTest(UserRole.NONE, "NONE");
	}
	
	@Test
	public void TestGetDisplayValue_WhenValueIsUnknown() {
		SetsDataSourceIntoControl();
		RunGetDisplayValueTest(Integer.MAX_VALUE, null);
	}
	
	@Test
	public void SetsSelection_OnSetControlValue_WhenValueIsExisting(){
		final int ExpectedIndex = 1;
		
		SetsDataSourceIntoControl();
		PowerMock.reset(fControl);
		fControl.select(ExpectedIndex);
		PowerMock.replayAll();
		
		fTestee.setControlValue("CUSTOMER");
	}
	
	@Test
	public void SetsNoSelection_OnSetControlValue_WhenValueIsNotExisting(){
		final int Value = Integer.MAX_VALUE;
		
		SetsDataSourceIntoControl();
		PowerMock.replayAll();
		
		fTestee.setControlValue(String.valueOf(Value));
	}
	
	@Test
	public void SetsNoSelection_OnSetControlValue_WhenValueIsNull(){
		PowerMock.replayAll();
		
		fTestee.setControlValue(null);
	}
	
	@Test
	public void ReturnsNull_OnGetControlValue_WhenSelectionIndexIsNegativ(){
		final int ExpectedState = -1;

		PowerMock.reset(fControl);
		expect(fControl.getSelectionIndex()).andReturn(ExpectedState);
		PowerMock.replayAll();
		
		assertThat(fTestee.getControlValue()).isNull();
	}
	
	@Test
	public void ReturnsCUSTOMER_OnGetControlValue_WhenValueIsCUSTOMER(){
		final int ExpectedState = 1;

		SetsDataSourceIntoControl();
		PowerMock.reset(fControl);
		expect(fControl.getSelectionIndex()).andReturn(ExpectedState);
		PowerMock.replayAll();
		
		assertThat(fTestee.getControlValue()).isEqualTo("CUSTOMER");
	}
	
	@Test
	public void DoesNothing_OnRemoveModifyListener() {
		PowerMock.reset(fControl);
		fControl.removeModifyListener(isA(ModifyListener.class));
		PowerMock.replayAll();
		
		
		fTestee.removeModifyListener(PowerMock.createMock(ModifyListener.class));
	}
}
