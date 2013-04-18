package ch.hslu.appe.fs1303.gui.labelprovider;

import org.eclipse.jface.viewers.LabelProvider;

import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;

public class PersonLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof String)
			return (String) element;
		
		if (!(element instanceof DTOPerson)) {
			return "";
		}
		DTOPerson person = (DTOPerson)element;
		
		return String.format("%s %s", person.getName(), person.getVorname());	
	}
}
