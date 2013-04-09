package ch.hslu.appe.fs1301.business.dto;

import java.util.Date;
import java.util.List;

/**
* Auto-Generated DTOs
* Tue Apr 09 13:33:55 CEST 2013
*/
public class DTOProdukt {
	private int id;
	private String bezeichnung;
	private int lagerbestand;
	private int minimalMenge;
	private int preis;
	private List<DTOBestellposition> bestellpositions;
	private List<DTOZentrallagerBestellung> zentrallagerBestellungs;

	public DTOProdukt() {
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getBezeichnung() {
		return this.bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}


	public int getLagerbestand() {
		return this.lagerbestand;
	}

	public void setLagerbestand(int lagerbestand) {
		this.lagerbestand = lagerbestand;
	}


	public int getMinimalMenge() {
		return this.minimalMenge;
	}

	public void setMinimalMenge(int minimalMenge) {
		this.minimalMenge = minimalMenge;
	}


	public int getPreis() {
		return this.preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}


	public List<DTOBestellposition> getBestellpositions() {
		return this.bestellpositions;
	}

	public void setBestellpositions(List<DTOBestellposition> bestellpositions) {
		this.bestellpositions = bestellpositions;
	}

	public DTOBestellposition addBestellposition(DTOBestellposition bestellposition) {
		getBestellpositions().add(bestellposition);
		bestellposition.setProdukt(this);

		return bestellposition;
	}

	public DTOBestellposition removeBestellposition(DTOBestellposition bestellposition) {
		getBestellpositions().remove(bestellposition);
		bestellposition.setProdukt(null);

		return bestellposition;
	}


	public List<DTOZentrallagerBestellung> getZentrallagerBestellungs() {
		return this.zentrallagerBestellungs;
	}

	public void setZentrallagerBestellungs(List<DTOZentrallagerBestellung> zentrallagerBestellungs) {
		this.zentrallagerBestellungs = zentrallagerBestellungs;
	}

	public DTOZentrallagerBestellung addZentrallagerBestellung(DTOZentrallagerBestellung zentrallagerBestellung) {
		getZentrallagerBestellungs().add(zentrallagerBestellung);
		zentrallagerBestellung.setProdukt(this);

		return zentrallagerBestellung;
	}

	public DTOZentrallagerBestellung removeZentrallagerBestellung(DTOZentrallagerBestellung zentrallagerBestellung) {
		getZentrallagerBestellungs().remove(zentrallagerBestellung);
		zentrallagerBestellung.setProdukt(null);

		return zentrallagerBestellung;
	}

}
