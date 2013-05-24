package ch.hslu.appe.fs1303.gui.models;

import java.util.List;

public class ZentralLagerEditorModel {

	private List<ZentralLagerWithProductModel> fBestellungen;
	
	public ZentralLagerEditorModel(List<ZentralLagerWithProductModel> bestellungen) {
		fBestellungen = bestellungen;
	}

	public List<ZentralLagerWithProductModel> getBestellungen() {
		return fBestellungen;
	}

	public void setBestellungen(List<ZentralLagerWithProductModel> bestellungen) {
		fBestellungen = bestellungen;
	}
}
