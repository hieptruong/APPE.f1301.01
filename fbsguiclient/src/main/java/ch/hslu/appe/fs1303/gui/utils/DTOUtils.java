package ch.hslu.appe.fs1303.gui.utils;

import org.eclipse.jface.viewers.LabelProvider;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1303.gui.labelprovider.OrderLabelProvider;
import ch.hslu.appe.fs1303.gui.labelprovider.PersonLabelProvider;
import ch.hslu.appe.fs1303.gui.labelprovider.ProductLabelProvider;
import ch.hslu.appe.fs1303.gui.models.quicksearch.PersonQuickSearchModel;
import ch.hslu.appe.fs1303.gui.models.quicksearch.ProductQuickSearchModel;
import ch.hslu.appe.fs1303.gui.models.quicksearch.iQuickSearchModel;

public class DTOUtils {
	private static LabelProvider fPersonLabelProvider;
	private static LabelProvider fOrderLabelProvider;
	private static LabelProvider fProductLabelProvider;
	
	private static iQuickSearchModel<DTOPerson> fPersonQuickSearchModel;
	
	static {
		fPersonLabelProvider = new PersonLabelProvider();
		fOrderLabelProvider = new OrderLabelProvider();
		fProductLabelProvider = new ProductLabelProvider();
		
		fPersonQuickSearchModel = new PersonQuickSearchModel();
	}
	
	public static LabelProvider getLabelProvider(Object obj) {
		if (obj == null) return null;
		
		if (obj instanceof DTOPerson) {
			return fPersonLabelProvider;
		} else if (obj instanceof DTOBestellung) {
			return fOrderLabelProvider;
		} else if (obj instanceof DTOProdukt) {
			return fProductLabelProvider;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static iQuickSearchModel getQuickSearchModel(Class<?> obj) {
		if (obj == null) return null;
		
		if (obj.equals(DTOPerson.class)) {
			return fPersonQuickSearchModel;
		} else if (obj.equals(DTOProdukt.class)) {
			return new ProductQuickSearchModel();
		} else {
			return null;
		}
	}
}
