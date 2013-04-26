package ch.hslu.appe.fs1303.gui.datasource;

import java.util.List;

import org.eclipse.swt.widgets.Table;

public interface iTableDescriptor<T> {
	public void createColumns(Table table);	
	public void createTableRows(Table table, List<T> data);
	public T getItem(int index);
}
