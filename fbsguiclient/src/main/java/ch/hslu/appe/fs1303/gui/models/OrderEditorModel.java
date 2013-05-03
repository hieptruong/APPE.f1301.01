package ch.hslu.appe.fs1303.gui.models;

import java.util.List;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;

public class OrderEditorModel {
	
	private DTOBestellung fBestellung;
	private List<BestellpositionWithProduktModel> fBestellposition;
	private DTOPerson fPerson;
	
	public OrderEditorModel(DTOBestellung bestellung, List<BestellpositionWithProduktModel> bestellpositionen, DTOPerson person)
	{
		fBestellung = bestellung;
		fBestellposition = bestellpositionen;
		fPerson = person;
	}

	public DTOBestellung getBestellung() {
		return fBestellung;
	}

	public void setBestellung(DTOBestellung bestellung) {
		fBestellung = bestellung;
	}

	public List<BestellpositionWithProduktModel> getBestellposition() {
		return fBestellposition;
	}

	public void setBestellposition(List<BestellpositionWithProduktModel> bestellposition) {
		fBestellposition = bestellposition;
	}

	public DTOPerson getPerson() {
		return fPerson;
	}

	public void setPerson(DTOPerson person) {
		fPerson = person;
	}
	

}
