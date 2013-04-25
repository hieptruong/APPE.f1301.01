package ch.hslu.appe.fs1301.gui.labelprovider;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1303.gui.labelprovider.PersonLabelProvider;

public class PersonLabelProviderTest {

	private PersonLabelProvider fTestee;

	@Before
	public void setUp() {
		fTestee = new PersonLabelProvider();
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
		final String Name = "Name";
		final String FirstName = "Bla";
		final String ExpectedString = Name + " " + FirstName;
		final DTOPerson objectToTranslate = new DTOPerson();
		objectToTranslate.setName(Name);
		objectToTranslate.setVorname(FirstName);
		
		String resultText = fTestee.getText(objectToTranslate);
		
		assertThat(resultText).isEqualTo(ExpectedString);
	}
}
