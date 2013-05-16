package ch.hslu.appe.fs1303.gui.datasource;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1303.gui.models.BestellpositionWithProduktModel;

public class OrderPositionTableDescriptorTest extends AbstractTableDescriptorTestHelper<BestellpositionWithProduktModel, BestellpositionTableDescriptor> {

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
		
		BestellpositionWithProduktModel dto1wm = new BestellpositionWithProduktModel(dto1, new DTOProdukt());
		DTOBestellposition dto2 = new DTOBestellposition();
		dto2.setId(10);
		dto2.setAnzahl(100);
		dto2.setProdukt(50);
		dto2.setStueckpreis(49);
		
		BestellpositionWithProduktModel dto2wm = new BestellpositionWithProduktModel(dto2, new DTOProdukt());
		
		List<BestellpositionWithProduktModel> data = new ArrayList<BestellpositionWithProduktModel>();
		data.add(dto1wm);
		data.add(dto2wm);
		
		runReturnsItemAtIndex(data);
	}
}
