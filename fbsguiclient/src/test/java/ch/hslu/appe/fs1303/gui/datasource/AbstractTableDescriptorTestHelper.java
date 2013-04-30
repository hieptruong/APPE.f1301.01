package ch.hslu.appe.fs1303.gui.datasource;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.fest.assertions.Assertions.assertThat;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.easymock.EasyMock;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

public abstract class AbstractTableDescriptorTestHelper<T, K extends AbstractTableDescriptor<T>> {

	protected K fTestee;
	protected Table fTable;
	protected TableColumn fColumn;
	private Class<K> fGenericClass;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		fGenericClass = (Class<K>) parameterizedType.getActualTypeArguments()[1];
		
		fTable = PowerMock.createMock(Table.class);
		fColumn = PowerMock.createMock(TableColumn.class);
		fTestee = PowerMock.createStrictPartialMock(fGenericClass, "createColumn", "createRow");
	}
	
	@After
	public void cleanUp() {
		PowerMock.niceReplayAndVerify();
	}
	
	protected void disposesExistingColumnsSetup() throws Exception {
		int ExistingColumnCount = 1;
		
		fTable.setRedraw(false);
		expect(fTable.getColumnCount()).andReturn(ExistingColumnCount--);
		expect(fTable.getColumns()).andReturn(new TableColumn[] { fColumn });
		fColumn.dispose();
		expect(fTable.getColumnCount()).andReturn(ExistingColumnCount);
		
		fTable.setRedraw(true);
	}
	
	public void runReturnsItemAtIndex(List<T> data) {
		fTable.removeAll();
		setupCreateRows(data.size());
		
		PowerMock.replayAll();
		
		fTestee.createTableRows(fTable, data);
		T result = fTestee.getItem(0);
		
		assertThat(result).isSameAs(data.get(0));
	}
	
	@Test
	public void DummyTest() throws Exception {
		fGenericClass.newInstance();
		assertThat(true).isTrue();
	}

	protected void setupCreateRows(int replaycount) {
		expect(fTestee.createRow(isA(Table.class), EasyMock.anyInt(), isA(String[].class))).andReturn(null).times(replaycount);
	}

	protected void setupNewColumn(String text) throws Exception {
		expect(fTestee.createColumn(isA(Table.class), EasyMock.anyInt(), EasyMock.eq(text), EasyMock.anyInt())).andReturn(null);
	}
}
