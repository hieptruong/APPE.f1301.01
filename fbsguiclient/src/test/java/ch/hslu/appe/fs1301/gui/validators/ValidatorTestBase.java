package ch.hslu.appe.fs1301.gui.validators;

import java.lang.reflect.ParameterizedType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ch.hslu.appe.fs1303.gui.validators.iValidator;

@RunWith(Parameterized.class)
public abstract class ValidatorTestBase<T extends iValidator> {

	protected T fTestee;
	protected boolean fNullable;
	protected boolean fExpectedResult;
	protected String fInput;
	private Class<T> fGenericClass;
	
	@SuppressWarnings("unchecked")
	public ValidatorTestBase(String input, boolean nullable, boolean expectedResult) {
		fInput = input;
		fNullable = nullable;
		fExpectedResult = expectedResult;
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		fGenericClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
	
	@Before
	public void setUp() {
		try {
			fTestee = fGenericClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestValidator() {
		boolean result = fTestee.validate(fInput, fNullable);
		
		Assert.assertEquals(fExpectedResult, result);
	}
}
