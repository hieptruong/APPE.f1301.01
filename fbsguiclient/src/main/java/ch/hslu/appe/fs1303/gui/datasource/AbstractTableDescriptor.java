package ch.hslu.appe.fs1303.gui.datasource;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public abstract class AbstractTableDescriptor<T> implements iTableDescriptor<T>, Comparator<T> {

	protected List<T> fData;
	
	protected TableColumn createColumn(Table table, int style, String text, int width) {
		TableColumn column = new TableColumn(table, style);
		column.setText(text);
		column.setWidth(width);
		return column;
	}
	
	protected TableItem createRow(Table table, int style, String[] strings) {
		TableItem item = new TableItem(table, style);
		item.setText(strings);
		return item;
	}
	
	@Override
	public void createColumns(Table table) {
		table.setRedraw(false);
		while ( table.getColumnCount() > 0 ) {
		    table.getColumns()[ 0 ].dispose();
		}
		
		doCreateColumns(table);
		
		table.setRedraw(true);
	}

	@Override
	public void createTableRows(Table table, List<T> data) {
		fData = data;
		table.removeAll();
		
		Collections.sort(data, this);
		
		for (T item : data) {
			doCreateRow(table, item);
		}
	}
	
	protected abstract void doCreateColumns(Table table);
	
	protected abstract void doCreateRow(Table table, T item);

	@Override
	public T getItem(int index) {
		return fData.get(index);
	}
}
