package ch.hslu.appe.fs1303.gui.models;

import java.util.List;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;

public class OrderEditorModel {
	
	private DTOBestellung fBestellung;
	private List<DTOBestellposition> fBestellposition;
	
	public OrderEditorModel(DTOBestellung bestellung, List<DTOBestellposition> bestellpositionen)
	{
		fBestellung = bestellung;
		fBestellposition = bestellpositionen;
	}

	public DTOBestellung getBestellung() {
		return fBestellung;
	}

	public void setBestellung(DTOBestellung bestellung) {
		fBestellung = bestellung;
	}

	public List<DTOBestellposition> getBestellposition() {
		return fBestellposition;
	}

	public void setBestellposition(List<DTOBestellposition> bestellposition) {
		fBestellposition = bestellposition;
	}
	

}
