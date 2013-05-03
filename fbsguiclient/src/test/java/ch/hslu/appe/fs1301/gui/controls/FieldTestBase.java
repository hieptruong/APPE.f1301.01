package ch.hslu.appe.fs1301.gui.controls;

import static org.fest.assertions.Assertions.assertThat;

import java.lang.reflect.ParameterizedType;

import org.junit.After;
import org.junit.Before;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1303.gui.controls.APPEControl;

public abstract class FieldTestBase<TControl extends APPEControl<TValue, ?>, TValue>{

	protected TControl fTestee;
	private Class<TControl> fGenericClass;
	
	@SuppressWarnings("unchecked")
	public FieldTestBase() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		fGenericClass = (Class<TControl>) parameterizedType.getActualTypeArguments()[0];
	}
	
	@Before
	public void setUp() {
		fTestee = PowerMock.createPartialMock(fGenericClass, "bindModel");
	}

	@After
	public void cleanUp(){
		PowerMock.niceReplayAndVerify();
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
