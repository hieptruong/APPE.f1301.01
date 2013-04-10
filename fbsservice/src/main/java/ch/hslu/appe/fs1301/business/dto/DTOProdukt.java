package ch.hslu.appe.fs1301.business.dto;

import java.util.List;

/**
* Auto-Generated DTOs
* Wed Apr 10 09:57:59 CEST 2013
*/
public class DTOProdukt {
	private int fId;
	private String fBezeichnung;
	private int fLagerbestand;
	private int fMinimalMenge;
	private int fPreis;
	private List<DTOBestellposition> fBestellpositions;
	private List<DTOZentrallagerBestellung> fZentrallagerBestellungs;

	public DTOProdukt() {
		
	}

	public int getId() {
		return fId;
	}

	public void setId(int id) {
		fId = id;
	}

	public String getBezeichnung() {
		return fBezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		fBezeichnung = bezeichnung;
	}

	public int getLagerbestand() {
		return fLagerbestand;
	}

	public void setLagerbestand(int lagerbestand) {
		fLagerbestand = lagerbestand;
	}

	public int getMinimalMenge() {
		return fMinimalMenge;
	}

	public void setMinimalMenge(int minimalMenge) {
		fMinimalMenge = minimalMenge;
	}

	public int getPreis() {
		return fPreis;
	}

	public void setPreis(int preis) {
		fPreis = preis;
	}

	public List<DTOBestellposition> getBestellpositions() {
		return fBestellpositions;
	}

	public void setBestellpositions(List<DTOBestellposition> bestellpositions) {
		fBestellpositions = bestellpositions;
	}

	public List<DTOZentrallagerBestellung> getZentrallagerBestellungs() {
		return fZentrallagerBestellungs;
	}

	public void setZentrallagerBestellungs(List<DTOZentrallagerBestellung> zentrallagerBestellungs) {
		fZentrallagerBestellungs = zentrallagerBestellungs;
	}

}