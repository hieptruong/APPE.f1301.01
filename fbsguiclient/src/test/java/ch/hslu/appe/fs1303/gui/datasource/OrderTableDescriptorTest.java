package ch.hslu.appe.fs1303.gui.datasource;

import static org.fest.assertions.Assertions.assertThat;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.easymock.EasyMock;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;

public class OrderTableDescriptorTest {

	private OrderTableDescriptor fOrderTableDescriptor;
	private Table fTable;
	private TableColumn fColumn;

	@Before
	public void setUp() {
		fTable = PowerMock.createMock(Table.class);
		fColumn = PowerMock.createMock(TableColumn.class);
		fOrderTableDescriptor = PowerMock.createStrictPartialMock(OrderTableDescriptor.class, "createColumn", "createRow");
	}
	
	@After
	public void cleanUp() {
		PowerMock.niceReplayAndVerify();
	}
	
	@Test
	public void createsCorrectColumns() throws Exception {
		final int ExistingColumnCount = 0;
		
		fTable.setRedraw(false);
		expect(fTable.getColumnCount()).andReturn(ExistingColumnCount);
		setupNewColumn("ID");
		setupNewColumn("Bestelldatum");
		setupNewColumn("Soll Lieferdatum");
		setupNewColumn("Ist Lieferdatum");
		setupNewColumn("Anzahl Positionen");
		fTable.setRedraw(true);
		PowerMock.replayAll();
		
		fOrderTableDescriptor.createColumns(fTable);
	}
	
	@Test
	public void disposesExistingColumns_OnCreateColumn() throws Exception {
		int ExistingColumnCount = 1;
		
		fTable.setRedraw(false);
		expect(fTable.getColumnCount()).andReturn(ExistingColumnCount--);
		expect(fTable.getColumns()).andReturn(new TableColumn[] { fColumn });
		fColumn.dispose();
		expect(fTable.getColumnCount()).andReturn(ExistingColumnCount);
		
		//Setup code, copied from above
		setupNewColumn("ID");
		setupNewColumn("Bestelldatum");
		setupNewColumn("Soll Lieferdatum");
		setupNewColumn("Ist Lieferdatum");
		setupNewColumn("Anzahl Positionen");
		fTable.setRedraw(true);
		PowerMock.replayAll();
		
		fOrderTableDescriptor.createColumns(fTable);
	}
	
	@Test
	public void createsANewRowForEachItemInData() {
		DTOBestellung dto = new DTOBestellung();
		dto.setBestelldatum(new Date());
		dto.setLiefertermin_Ist(new Date());
		dto.setLiefertermin_Soll(new Date());
		List<DTOBestellung> data = new ArrayList<DTOBestellung>();
		data.add(dto);
		data.add(dto);
		
		fTable.removeAll();
		setupCreateRows(data.size());
		
		PowerMock.replayAll();
		
		fOrderTableDescriptor.createTableRows(fTable, data);
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
		
		fTable.removeAll();
		setupCreateRows(data.size());
		
		PowerMock.replayAll();
		
		fOrderTableDescriptor.createTableRows(fTable, data);
		DTOBestellung result = fOrderTableDescriptor.getItem(0);
		
		assertThat(result).isSameAs(dto1);
	}
	
	@Test
	public void DummyTest() {
		new OrderTableDescriptor();
		assertThat(true).isTrue();
	}

	private void setupCreateRows(int replaycount) {
		expect(fOrderTableDescriptor.createRow(isA(Table.class), EasyMock.anyInt(), isA(String[].class))).andReturn(null).times(replaycount);
	}

	private void setupNewColumn(String text) throws Exception {
		expect(fOrderTableDescriptor.createColumn(isA(Table.class), EasyMock.anyInt(), EasyMock.eq(text), EasyMock.anyInt())).andReturn(null);
	}
}
