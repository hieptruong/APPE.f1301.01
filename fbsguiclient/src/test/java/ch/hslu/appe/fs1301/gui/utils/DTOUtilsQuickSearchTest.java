package ch.hslu.appe.fs1301.gui.utils;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1303.gui.GuiModule;
import ch.hslu.appe.fs1303.gui.models.quicksearch.PersonQuickSearchModel;
import ch.hslu.appe.fs1303.gui.models.quicksearch.iQuickSearchModel;
import ch.hslu.appe.fs1303.gui.utils.DTOUtils;

import com.google.inject.Injector;

@RunWith(Parameterized.class)
public class DTOUtilsQuickSearchTest {

	private Class<?> fquickSearchModelClazz;
	private Class<?> fDtoClazz;
	
	public DTOUtilsQuickSearchTest(Class<?> dtoClazz, Class<?> quickSearchModelClazz) {
		fDtoClazz = dtoClazz;
		fquickSearchModelClazz = quickSearchModelClazz;
	}
	
	@Before
	public void setUp() throws Exception {
		Injector injectorMock = PowerMock.createMock(Injector.class);
		GuiModule.injector = injectorMock;
		injectorMock.injectMembers(EasyMock.anyObject());
		PowerMock.replayAll();
	}
	
	@SuppressWarnings("rawtypes")
	@Parameters
	public static Collection getParameters() {
		Object[][] list = new Object[][] {
			{DTOPerson.class, PersonQuickSearchModel.class},
			{Object.class, null},	
			{null, null},	
		};
		return Arrays.asList(list);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void ReturnsCorrectQuickSearchModel() {
		iQuickSearchModel quickSearchModel = DTOUtils.getQuickSearchModel(fDtoClazz);
		if (fquickSearchModelClazz == null) {
			assertThat(quickSearchModel).isNull();
		} else {
			assertThat(quickSearchModel).isInstanceOf(fquickSearchModelClazz);
		}
	}
}
