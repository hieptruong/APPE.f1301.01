package ch.hslu.appe.fs1303.gui.models;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;

public class NewOrderPositionWizardModel {
	
	private DTOBestellposition fPosition;
	private DTOProdukt fProdukt;

	public NewOrderPositionWizardModel(DTOBestellposition position, DTOProdukt produkt) {
		fPosition = position;
		setProdukt(produkt);
	}

	public DTOBestellposition getPosition() {
		return fPosition;
	}

	public void setPosition(DTOBestellposition position) {
		fPosition = position;
	}

	public DTOProdukt getProdukt() {
		return fProdukt;
	}

	public void setProdukt(DTOProdukt produkt) {
		fProdukt = produkt;
	}
}
