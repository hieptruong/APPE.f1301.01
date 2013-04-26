package ch.hslu.appe.fs1303.gui.datasource;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1303.gui.utils.DateUtils;

public class OrderTableDescriptor implements iTableDescriptor<DTOBestellung> {
	
	private TableColumn fIdColumn;
	private TableColumn fOrderDate;
	private TableColumn fDeliveryDateShould;
	private TableColumn fDeliveryDateIs;
	private TableColumn fItemCount;
	private List<DTOBestellung> fData;

	public void createColumns(Table table) {
		table.setRedraw(false);
		while ( table.getColumnCount() > 0 ) {
		    table.getColumns()[ 0 ].dispose();
		}
		
		
		fIdColumn = new TableColumn(table, SWT.LEFT);
		fIdColumn.setText("ID");
		fIdColumn.setWidth(30);
		
		fOrderDate = new TableColumn(table, SWT.CENTER);
		fOrderDate.setText("Bestelldatum");
		fOrderDate.setWidth(150);
		
		fDeliveryDateShould = new TableColumn(table, SWT.CENTER);
		fDeliveryDateShould.setText("Soll Lieferdatum");
		fDeliveryDateShould.setWidth(150);
		
		fDeliveryDateIs = new TableColumn(table, SWT.CENTER);
		fDeliveryDateIs.setText("Ist Lieferdatum");
		fDeliveryDateIs.setWidth(150);
		
		fItemCount = new TableColumn(table, SWT.CENTER);
		fItemCount.setText("Anzahl Positionen");
		fItemCount.setWidth(150);
		
		table.setRedraw(true);
	}

	@Override
	public void createTableRows(Table table, List<DTOBestellung> data) {
		fData = data;
		table.removeAll();
		
		Collections.sort(data, new Comparator<DTOBestellung>() {

			@Override
			public int compare(DTOBestellung o1, DTOBestellung o2) {
				return o1.getBestelldatum().compareTo(o2.getBestelldatum());
			}
		});
		
		for (DTOBestellung bestellung : data) {
			TableItem item = new TableItem(table, SWT.None);
			item.setText(new String[] { String.valueOf(bestellung.getId()),
					DateUtils.getDateAsString(bestellung.getBestelldatum()),
					DateUtils.getDateAsString(bestellung.getLiefertermin_Soll()),
					DateUtils.getDateAsString(bestellung.getLiefertermin_Ist()),
					String.valueOf(bestellung.getBestellpositions().size())});
		}
	}

	@Override
	public DTOBestellung getItem(int index) {
		return fData.get(index);
	}
	
	
}
