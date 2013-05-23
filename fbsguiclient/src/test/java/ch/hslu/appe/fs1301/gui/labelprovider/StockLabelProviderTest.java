package ch.hslu.appe.fs1301.gui.labelprovider;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.appe.fs1301.business.shared.dto.DTOZentrallagerBestellung;
import ch.hslu.appe.fs1303.gui.labelprovider.ZentralLagerLabelProvider;

public class StockLabelProviderTest {

	private ZentralLagerLabelProvider fTestee;

	@Before
	public void setUp() {
		fTestee = new ZentralLagerLabelProvider();
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
		final int Anzahl = 1;
		final String ExpectedString = Id + ": " + Anzahl;
		final DTOZentrallagerBestellung objectToTranslate = new DTOZentrallagerBestellung();
		objectToTranslate.setId(Id);
		objectToTranslate.setAnzahl(Anzahl);
		
		String resultText = fTestee.getText(objectToTranslate);
		
		assertThat(resultText).isEqualTo(ExpectedString);
	}
}
