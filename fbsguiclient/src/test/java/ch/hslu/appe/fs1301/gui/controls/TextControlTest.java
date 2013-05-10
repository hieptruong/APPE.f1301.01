package ch.hslu.appe.fs1301.gui.controls;

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;

import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1303.gui.controls.APPEStringField;

public class TextControlTest extends FieldTestBase<APPEStringField, Text, String>{

	protected Text doMockCreateControl() {
		return mockCreateControlForTextBox();
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
	public void GetsControlValue() {
		final String value = "Value";
		PowerMock.reset(fControl);
		expect(fControl.getText()).andReturn(value);
		PowerMock.replayAll();
		
		assertThat(fTestee.getControlValue()).isEqualTo(value);
	}
	
	@Test 
	public void SetsControlValue() {
		final String value = "Value";
		PowerMock.reset(fControl);
		fControl.setText(value);
		PowerMock.replayAll();
		
		fTestee.setControlValue(value);
	}
}
