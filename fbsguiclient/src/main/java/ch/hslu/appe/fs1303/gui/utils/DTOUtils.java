package ch.hslu.appe.fs1303.gui.utils;

import org.eclipse.jface.viewers.LabelProvider;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1303.gui.labelprovider.OrderLabelProvider;
import ch.hslu.appe.fs1303.gui.labelprovider.PersonLabelProvider;
import ch.hslu.appe.fs1303.gui.labelprovider.ProductLabelProvider;
import ch.hslu.appe.fs1303.gui.models.quicksearch.PersonQuickSearchModel;
import ch.hslu.appe.fs1303.gui.models.quicksearch.iQuickSearchModel;

public class DTOUtils {
	public static LabelProvider getLabelProvider(Object obj) {
		if (obj == null) return null;
		
		if (obj instanceof DTOPerson) {
			return new PersonLabelProvider();
		} else if (obj instanceof DTOBestellung) {
			return new OrderLabelProvider();
		} else if (obj instanceof DTOProdukt) {
			return new ProductLabelProvider();
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static iQuickSearchModel getQuickSearchModel(Class<?> obj) {
		if (obj == null) return null;
		
		if (obj.equals(DTOPerson.class)) {
			return new PersonQuickSearchModel();
		} else {
			return null;
		}
	}
}
