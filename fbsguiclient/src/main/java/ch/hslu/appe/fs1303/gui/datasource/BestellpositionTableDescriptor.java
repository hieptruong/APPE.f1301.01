package ch.hslu.appe.fs1303.gui.datasource;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1303.gui.utils.DateUtils;

public class BestellpositionTableDescriptor implements iTableDescriptor<DTOBestellposition> {
	
	private TableColumn fIdColumn;
	private TableColumn fCount;
	private TableColumn fPiecePrice;
	private TableColumn fProductID;
	private List<DTOBestellposition> fData;

	public void createColumns(Table table) {
		table.setRedraw(false);
		while ( table.getColumnCount() > 0 ) {
		    table.getColumns()[ 0 ].dispose();
		}		
		
		fIdColumn = new TableColumn(table, SWT.LEFT);
		fIdColumn.setText("ID");
		fIdColumn.setWidth(30);
		
		fCount = new TableColumn(table, SWT.CENTER);
		fCount.setText("Anzahl");
		fCount.setWidth(150);
		
		fPiecePrice = new TableColumn(table, SWT.CENTER);
		fPiecePrice.setText("Stueckpreis");
		fPiecePrice.setWidth(150);
		
		fProductID = new TableColumn(table, SWT.CENTER);
		fProductID.setText("ProduktID");
		fProductID.setWidth(150);
		
		table.setRedraw(true);
	}

	@Override
	public void createTableRows(Table table, List<DTOBestellposition> data) {
		fData = data;
		table.removeAll();
		
		Collections.sort(data, new Comparator<DTOBestellposition>() {

			@Override
			public int compare(DTOBestellposition o1, DTOBestellposition o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		
		for (DTOBestellposition bestellposition : data) {
			TableItem item = new TableItem(table, SWT.None);
			item.setText(new String[] { String.valueOf(bestellposition.getId()),
					String.valueOf(bestellposition.getAnzahl()),
					String.valueOf(bestellposition.getStueckpreis()),
					String.valueOf(bestellposition.getProdukt())});
		}
	}

	@Override
	public DTOBestellposition getItem(int index) {
		return fData.get(index);
	}
	
	
}
