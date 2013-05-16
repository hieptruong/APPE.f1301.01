package ch.hslu.appe.fs1303.gui.datasource;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

import ch.hslu.appe.fs1303.gui.models.BestellpositionWithProduktModel;

public class BestellpositionTableDescriptor extends AbstractTableDescriptor<BestellpositionWithProduktModel> {
	
	@Override
	protected void doCreateColumns(Table table) {
		createColumn(table, SWT.LEFT, "ID", 30);
		createColumn(table, SWT.CENTER, "Anzahl", 150);
		createColumn(table, SWT.CENTER, "Stueckpreis", 150);
		createColumn(table, SWT.CENTER, "ProduktID", 150);
	}

	@Override
	protected void doCreateRow(Table table, BestellpositionWithProduktModel item) {
		createRow(table, SWT.None, new String[] { 
				String.valueOf(item.getBestellposition().getId()),
				String.valueOf(item.getBestellposition().getAnzahl()),
				String.valueOf(item.getBestellposition().getStueckpreis()),
				String.valueOf(item.getProdukt().getBezeichnung())});
	}

	@Override
	public int compare(BestellpositionWithProduktModel o1, BestellpositionWithProduktModel o2) {
		if (o1.getBestellposition().getId() == null) return -1;
		if (o2.getBestellposition().getId() == null) return 1;
		return o1.getBestellposition().getId().compareTo(o2.getBestellposition().getId());
	}	
}
