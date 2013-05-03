package ch.hslu.appe.fs1303.gui.models;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;

public class BestellpositionWithProduktModel {

	private DTOBestellposition fBestellposition;
	private DTOProdukt fProdukt;

	public BestellpositionWithProduktModel(DTOBestellposition position, DTOProdukt produkt) {
		fBestellposition = position;
		fProdukt = produkt;
		
	}

	public DTOBestellposition getBestellposition() {
		return fBestellposition;
	}

	public void setBestellposition(DTOBestellposition bestellposition) {
		fBestellposition = bestellposition;
	}

	public DTOProdukt getProdukt() {
		return fProdukt;
	}

	public void setProdukt(DTOProdukt produkt) {
		fProdukt = produkt;
	}
}
