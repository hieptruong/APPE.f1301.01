package ch.hslu.appe.fs1303.gui.models;

import java.util.List;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;

public class PersonEditorModel {
	private DTOPerson fPerson;
	private List<DTOBestellung> fOrders;
	private List<DTOBestellung> fCreatedOrders;

	public PersonEditorModel(DTOPerson person, List<DTOBestellung> orders, List<DTOBestellung> createdOrders) {
		fPerson = person;
		fOrders = orders;		
		fCreatedOrders = createdOrders;
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

	public List<DTOBestellung> getCreatedOrders() {
		return fCreatedOrders;
	}

	public void setCreatedOrders(List<DTOBestellung> fCreatedOrders) {
		this.fCreatedOrders = fCreatedOrders;
	}
	
	
	
}
