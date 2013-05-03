package ch.hslu.appe.fs1301.gui.utils;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jface.viewers.LabelProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.powermock.api.easymock.PowerMock;

import com.google.inject.Injector;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1303.gui.GuiModule;
import ch.hslu.appe.fs1303.gui.labelprovider.OrderLabelProvider;
import ch.hslu.appe.fs1303.gui.labelprovider.PersonLabelProvider;
import ch.hslu.appe.fs1303.gui.labelprovider.ProductLabelProvider;
import ch.hslu.appe.fs1303.gui.utils.DTOUtils;

@RunWith(Parameterized.class)
public class DTOUtilsLabelProviderTest {

	private Class<?> fClazz;
	private Object fObject;
	
	public DTOUtilsLabelProviderTest(Object object, Class<?> clazz) {
		fObject = object;
		fClazz = clazz;
	}
	
	
	@SuppressWarnings("rawtypes")
	@Parameters
	public static Collection getParameters() {
		Object[][] list = new Object[][] {
			{new DTOPerson(), PersonLabelProvider.class},	
			{new DTOBestellung(), OrderLabelProvider.class},	
			{new DTOProdukt(), ProductLabelProvider.class},
			{new Object(), null},	
			{null, null},	
		};
		return Arrays.asList(list);
	}

	@Test
	public void ReturnsCorrectLabelProvider() {
		Injector injectorMock = PowerMock.createMock(Injector.class);
		GuiModule.injector = injectorMock;
		
		LabelProvider provider = DTOUtils.getLabelProvider(fObject);
		if (fClazz == null) {
			assertThat(provider).isNull();
		} else {
			assertThat(provider).isInstanceOf(fClazz);
		}
	}
}
