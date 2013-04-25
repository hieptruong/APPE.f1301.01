package ch.hslu.appe.fs1303.gui.labelprovider;

import org.eclipse.jface.viewers.LabelProvider;

import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;

public class ProductLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof String)
			return (String) element;
		
		if (!(element instanceof DTOProdukt)) {
			return "";
		}
		DTOProdukt product = (DTOProdukt)element;
		
		return String.format("%s", product.getBezeichnung());
	}
}
