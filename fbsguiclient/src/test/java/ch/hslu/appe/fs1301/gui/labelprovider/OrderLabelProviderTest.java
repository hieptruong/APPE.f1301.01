package ch.hslu.appe.fs1301.gui.labelprovider;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1303.gui.labelprovider.OrderLabelProvider;
import ch.hslu.appe.fs1303.gui.utils.DateUtils;

public class OrderLabelProviderTest {

	private OrderLabelProvider fTestee;

	@Before
	public void setUp() {
		fTestee = new OrderLabelProvider();
	}
	
	@Test
	public void returnsSameString_WhenInvokedWithAString() {
		final String ExpectedString = "Test";
		
		String resultText = fTestee.getText(ExpectedString);
		
		assertThat(resultText).isSameAs(ExpectedString);
	}
	
	@Test
	public void returnsEmptyString_WhenInvokedWithWrongObject() {
		final String ExpectedString = "";
		final Object objectToTranslate = new Object();
		
		String resultText = fTestee.getText(objectToTranslate);
		
		assertThat(resultText).isEqualTo(ExpectedString);
	}
	
	@Test
	public void returnsExpectedString_WhenInvokedWithCorrectObject() {
		final int Id = 15;
		final Date Date = new Date();
		final String ExpectedString = Id + ": " + DateUtils.getDateTimeAsString(Date);
		final DTOBestellung objectToTranslate = new DTOBestellung();
		objectToTranslate.setId(Id);
		objectToTranslate.setBestelldatum(Date);
		
		String resultText = fTestee.getText(objectToTranslate);
		
		assertThat(resultText).isEqualTo(ExpectedString);
	}
}
