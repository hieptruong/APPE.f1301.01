package ch.hslu.appe.fs1303.gui.labelprovider;

import org.eclipse.jface.viewers.LabelProvider;

import ch.hslu.appe.fs1301.business.shared.dto.DTOZentrallagerBestellung;

public class ZentralLagerLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof String)
			return (String) element;
		
		if (!(element instanceof DTOZentrallagerBestellung)) {
			return "";
		}
		DTOZentrallagerBestellung zentralLager = (DTOZentrallagerBestellung)element;
		
		return String.format("%d: %s", zentralLager.getId(), zentralLager.getAnzahl());
	}
}
