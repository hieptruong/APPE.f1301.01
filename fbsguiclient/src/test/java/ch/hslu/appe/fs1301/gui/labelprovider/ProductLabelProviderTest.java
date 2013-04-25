package ch.hslu.appe.fs1301.gui.labelprovider;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1303.gui.labelprovider.ProductLabelProvider;

public class ProductLabelProviderTest {

	private ProductLabelProvider fTestee;

	@Before
	public void setUp() {
		fTestee = new ProductLabelProvider();
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
		final String Description = "Description";
		final String ExpectedString = Description;
		final DTOProdukt objectToTranslate = new DTOProdukt();
		objectToTranslate.setBezeichnung(Description);
		
		String resultText = fTestee.getText(objectToTranslate);
		
		assertThat(resultText).isEqualTo(ExpectedString);
	}
}
