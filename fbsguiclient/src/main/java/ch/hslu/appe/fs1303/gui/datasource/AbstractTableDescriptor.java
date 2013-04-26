package ch.hslu.appe.fs1303.gui.datasource;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public abstract class AbstractTableDescriptor {

	protected TableColumn createColumn(Table table, int style, String text, int width) {
		TableColumn column = new TableColumn(table, style);
		column.setText(text);
		column.setWidth(width);
		return column;
	}
}
