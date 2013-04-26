package ch.hslu.appe.fs1303.gui.datasource;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;

import org.easymock.EasyMock;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

public class OrderTableDescriptorTest {

	private OrderTableDescriptor fOrderTableDescriptor;
	private Table fTable;
	private TableColumn fColumn;

	@Before
	public void setUp() {
		fTable = PowerMock.createMock(Table.class);
		fColumn = PowerMock.createMock(TableColumn.class);
		fOrderTableDescriptor = PowerMock.createStrictPartialMock(OrderTableDescriptor.class, "createColumn");
	}
	
	@After
	public void cleanUp() {
		PowerMock.verifyAll();
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

	private void setupNewColumn(String text) throws Exception {
		expect(fOrderTableDescriptor.createColumn(isA(Table.class), EasyMock.anyInt(), EasyMock.eq(text), EasyMock.anyInt())).andReturn(null);
	}
}
