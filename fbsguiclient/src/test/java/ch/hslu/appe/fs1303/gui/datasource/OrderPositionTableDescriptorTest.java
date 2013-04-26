package ch.hslu.appe.fs1303.gui.datasource;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;

public class OrderPositionTableDescriptorTest extends AbstractTableDescriptorTestHelper<DTOBestellposition, BestellpositionTableDescriptor> {

	@Test
	public void disposesExistingColumns_OnCreateColumn() throws Exception {
		disposesExistingColumnsSetup();
		
		//Setup code, copied from above
		setupNewColumn("ID");
		setupNewColumn("Anzahl");
		setupNewColumn("Stueckpreis");
		setupNewColumn("ProduktID");

		PowerMock.replayAll();
		
		fTestee.createColumns(fTable);
	}
	
	@Test
	public void returnsItemAtIndex_OnGetItem() {
		DTOBestellposition dto1 = new DTOBestellposition();
		dto1.setId(5);
		dto1.setAnzahl(10);
		dto1.setProdukt(5);
		dto1.setStueckpreis(99);
		DTOBestellposition dto2 = new DTOBestellposition();
		dto2.setId(10);
		dto2.setAnzahl(100);
		dto2.setProdukt(50);
		dto2.setStueckpreis(49);
		
		List<DTOBestellposition> data = new ArrayList<DTOBestellposition>();
		data.add(dto1);
		data.add(dto2);
		
		runReturnsItemAtIndex(data);
	}
}
