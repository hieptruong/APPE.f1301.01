package ch.hslu.appe.fs1303.gui.controls;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.fest.assertions.Assertions.assertThat;

import java.lang.reflect.ParameterizedType;

import org.easymock.EasyMock;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.junit.After;
import org.junit.Before;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1303.gui.controls.APPEControl;

public abstract class FieldTestBase<TAPPEControl extends APPEControl<TValue, TControl>, TControl extends Control, TValue>{

	protected TAPPEControl fTestee;
	private Class<TAPPEControl> fGenericClass;
	protected Composite fParent;
	protected FormToolkit fToolkit;
	protected String fLabelText;
	protected int fStyle;
	protected TControl fControl;
	
	@SuppressWarnings("unchecked")
	public FieldTestBase() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		fGenericClass = (Class<TAPPEControl>) parameterizedType.getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Throwable {
		fParent = PowerMock.createMock(Composite.class);
		fToolkit = PowerMock.createMock(FormToolkit.class);
		fLabelText = "Bla";
		fStyle = 0;
		
		Label label = PowerMock.createMock(Label.class);
		expect(fToolkit.createLabel(fParent, fLabelText)).andReturn(label);
		label.setLayoutData(isA(GridData.class));
		
		fControl = doMockCreateControl();
		
		PowerMock.replayAll();
		fTestee = (TAPPEControl) fGenericClass.getDeclaredConstructors()[0].newInstance(fParent, fToolkit, fLabelText, fStyle);
	}
	
	protected abstract TControl doMockCreateControl() throws Exception;

	@After
	public void cleanUp(){
		PowerMock.niceReplayAndVerify();
	}
	
	protected Button mockCreateControlForButton() {
		Button button = PowerMock.createMock(Button.class);
		expect(fToolkit.createButton(fParent, "", fStyle | SWT.CHECK)).andReturn(button);
		button.setLayoutData(isA(GridData.class));
		button.addListener(EasyMock.anyInt(), isA(Listener.class));
		return button;
	}
	
	protected Text mockCreateControlForTextBox() {
		Text text = PowerMock.createMock(Text.class);
		expect(fToolkit.createText(fParent, null, fStyle)).andReturn(text);
		text.setLayoutData(isA(GridData.class));
		text.addModifyListener(isA(ModifyListener.class));
		return text;
	}
	
	protected void RunBaseClassTest(Class<?> clazz) {
		assertThat(fTestee.getClass().isAssignableFrom(clazz));
	}
	
	protected void RunFieldClassTest(Class<TValue> clazz) {
		assertThat(fTestee.getFieldClass()).isEqualTo(clazz);
	}
	
	protected void RunGetValueForModelTest(String theString, TValue modelValue) {
		assertThat(fTestee.getValueForModel(theString)).isEqualTo(modelValue);
	}
	
	protected void RunGetDisplayValueTest(TValue value, String displayValue) {
		assertThat(fTestee.getDisplayValue(value)).isEqualTo(displayValue);
	}
}
