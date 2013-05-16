package ch.hslu.appe.fs1301.gui.utils;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import com.google.inject.Injector;

import ch.hslu.appe.fs1303.gui.GuiModule;
import ch.hslu.appe.fs1303.gui.utils.ArrayUtils;
import ch.hslu.appe.fs1303.gui.utils.DTOUtils;
import ch.hslu.appe.fs1303.gui.utils.DateUtils;
import ch.hslu.appe.fs1303.gui.utils.ErrorUtils;

public class DummyTest {

	@Test
	public void createDateUtils() {
		DateUtils dateUtils = new DateUtils();
		assertNotNull(dateUtils);
	}
	
	@Test
	public void createErrorUtils() {
		ErrorUtils errorUtils = new ErrorUtils();
		assertNotNull(errorUtils);
	}

	@Test
	public void createArrayUtils() {
		ArrayUtils arrayUtils = new ArrayUtils();
		assertNotNull(arrayUtils);
	}
	
	@Test
	public void createDTOUtils() {
		Injector injectorMock = PowerMock.createMock(Injector.class);
		GuiModule.injector = injectorMock;
		DTOUtils dtoUtils = new DTOUtils();
		assertNotNull(dtoUtils);
	}
}
