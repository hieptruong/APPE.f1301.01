package ch.hslu.appe.fs1303.gui.datasource;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;

public class BestellpositionTableDescriptor extends AbstractTableDescriptor<DTOBestellposition> {
	
	@Override
	protected void doCreateColumns(Table table) {
		createColumn(table, SWT.LEFT, "ID", 30);
		createColumn(table, SWT.CENTER, "Anzahl", 150);
		createColumn(table, SWT.CENTER, "Stueckpreis", 150);
		createColumn(table, SWT.CENTER, "ProduktID", 150);
	}

	@Override
	protected void doCreateRow(Table table, DTOBestellposition item) {
		createRow(table, SWT.None, new String[] { 
				String.valueOf(item.getId()),
				String.valueOf(item.getAnzahl()),
				String.valueOf(item.getStueckpreis()),
				String.valueOf(item.getProdukt())});
	}

	@Override
	public int compare(DTOBestellposition o1, DTOBestellposition o2) {
		return o1.getId().compareTo(o2.getId());
	}	
}
