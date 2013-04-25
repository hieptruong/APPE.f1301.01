package ch.hslu.appe.fs1303.gui.models;

import java.util.List;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;

public class PersonEditorModel {
	private DTOPerson fPerson;
	private List<DTOBestellung> fOrders;

	public PersonEditorModel(DTOPerson person, List<DTOBestellung> orders) {
		fPerson = person;
		fOrders = orders;		
	}
	
	public DTOPerson getPerson() {
		return fPerson;
	}

	public void setPerson(DTOPerson person) {
		fPerson = person;
	}

	public List<DTOBestellung> getOrders() {
		return fOrders;
	}

	public void setOrders(List<DTOBestellung> orders) {
		fOrders = orders;
	}
	
}
