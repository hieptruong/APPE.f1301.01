package ch.hslu.appe.fs1303.gui.models.quicksearch;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;

public class PersonQuickSearchModel extends QuickSearchModel<DTOPerson> {

	@Inject
	iPersonAPI personAPI;
	
	@Override
	public List<DTOPerson> search(String filter) {
		try {
			return personAPI.getCustomersByName(filter);
		} catch (AccessDeniedException e) {
			return new ArrayList<DTOPerson>();
		}
	}

}
