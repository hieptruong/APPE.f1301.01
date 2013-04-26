package ch.hslu.appe.fs1303.gui.labelprovider;

import org.eclipse.jface.viewers.LabelProvider;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1303.gui.utils.DateUtils;

public class OrderLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof String)
			return (String) element;
		
		if (!(element instanceof DTOBestellung)) {
			return "";
		}
		DTOBestellung bestellung = (DTOBestellung)element;
		
		return String.format("%d: %s", bestellung.getId(), DateUtils.getDateTimeAsString(bestellung.getBestelldatum()));	
	}
}
