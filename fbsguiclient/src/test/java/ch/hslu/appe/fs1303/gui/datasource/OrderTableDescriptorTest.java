package ch.hslu.appe.fs1303.gui.datasource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;

public class OrderTableDescriptorTest extends AbstractTableDescriptorTestHelper<DTOBestellung, OrderTableDescriptor>{

	@Test
	public void disposesExistingColumns_OnCreateColumn() throws Exception {
		disposesExistingColumnsSetup();

		setupNewColumn("ID");
		setupNewColumn("Bestelldatum");
		setupNewColumn("Soll Lieferdatum");
		setupNewColumn("Ist Lieferdatum");
		setupNewColumn("Anzahl Positionen");
		PowerMock.replayAll();
		
		fTestee.createColumns(fTable);
	}
	
	@Test
	public void returnsItemAtIndex_OnGetItem() {
		DTOBestellung dto1 = new DTOBestellung();
		dto1.setBestelldatum(new Date());
		dto1.setLiefertermin_Ist(new Date());
		dto1.setLiefertermin_Soll(new Date());
		DTOBestellung dto2 = new DTOBestellung();
		dto2.setBestelldatum(new Date());
		dto2.setLiefertermin_Ist(new Date());
		dto2.setLiefertermin_Soll(new Date());
		List<DTOBestellung> data = new ArrayList<DTOBestellung>();
		data.add(dto1);
		data.add(dto2);
		
		runReturnsItemAtIndex(data);
	}
}
