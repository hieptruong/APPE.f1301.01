package ch.hslu.appe.fs1303.gui.datasource;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1303.gui.utils.DateUtils;

public class OrderTableDescriptor extends AbstractTableDescriptor<DTOBestellung>{

	@Override
	protected void doCreateColumns(Table table) {

		createColumn(table, SWT.None, "ID", 30);
		createColumn(table, SWT.CENTER, "Bestelldatum", 150);
		createColumn(table, SWT.CENTER, "Soll Lieferdatum", 150);
		createColumn(table, SWT.CENTER, "Ist Lieferdatum", 150);
		createColumn(table, SWT.CENTER, "Anzahl Positionen", 150);
	}

	@Override
	protected void doCreateRow(Table table, DTOBestellung item) {
		createRow(table, SWT.None, new String[] { 
				String.valueOf(item.getId()),
				DateUtils.getDateAsString(item.getBestelldatum()),
				DateUtils.getDateAsString(item.getLiefertermin_Soll()),
				DateUtils.getDateAsString(item.getLiefertermin_Ist()),
				String.valueOf(item.getBestellpositions().size())});
	}
	
	@Override
	public int compare(DTOBestellung o1, DTOBestellung o2) {
		return o1.getBestelldatum().compareTo(o2.getBestelldatum());
	}
}
