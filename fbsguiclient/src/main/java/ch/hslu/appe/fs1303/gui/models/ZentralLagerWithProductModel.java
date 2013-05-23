package ch.hslu.appe.fs1303.gui.models;

import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1301.business.shared.dto.DTOZentrallagerBestellung;

public class ZentralLagerWithProductModel {
	private DTOZentrallagerBestellung fBestellung;
	private DTOProdukt fProdukt;

	public ZentralLagerWithProductModel(DTOZentrallagerBestellung bestellung, DTOProdukt produkt) {
		fBestellung = bestellung;
		fProdukt = produkt;
		
	}

	public DTOZentrallagerBestellung getBestellung() {
		return fBestellung;
	}

	public void setBestellung(DTOZentrallagerBestellung bestellung) {
		fBestellung = bestellung;
	}

	public DTOProdukt getProdukt() {
		return fProdukt;
	}

	public void setProdukt(DTOProdukt produkt) {
		fProdukt = produkt;
	}
}
