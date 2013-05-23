package ch.hslu.appe.fs1303.gui.datasource;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

import ch.hslu.appe.fs1303.gui.models.ZentralLagerWithProductModel;
import ch.hslu.appe.fs1303.gui.utils.DateUtils;

public class ZentralLagerTableDescriptor extends AbstractTableDescriptor<ZentralLagerWithProductModel> {

	@Override
	public int compare(ZentralLagerWithProductModel o1,
			ZentralLagerWithProductModel o2) {
		if (o1.getBestellung() == null) return -1;
		if (o2.getBestellung() == null) return 1;
		return o1.getBestellung().getLiefertermin().compareTo(o2.getBestellung().getLiefertermin());
	}

	@Override
	protected void doCreateColumns(Table table) {
		createColumn(table, SWT.None, "ID", 30);
		createColumn(table, SWT.CENTER, "Liefertermin", 150);
		createColumn(table, SWT.CENTER, "Produkt", 150);
		createColumn(table, SWT.CENTER, "Anzahl", 150);
	}

	@Override
	protected void doCreateRow(Table table, ZentralLagerWithProductModel item) {
		createRow(table, SWT.None, new String[] { 
				String.valueOf(item.getBestellung().getId()),
				DateUtils.getDateAsString(item.getBestellung().getLiefertermin()),
				item.getProdukt().getBezeichnung(),
				String.valueOf(item.getBestellung().getAnzahl())});
	}

}
