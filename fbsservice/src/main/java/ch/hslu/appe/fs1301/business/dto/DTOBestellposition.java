package ch.hslu.appe.fs1301.business.dto;

import ch.hslu.appe.fs1301.data.shared.Bestellposition;
import ch.hslu.appe.fs1301.data.shared.Bestellung;
import ch.hslu.appe.fs1301.data.shared.Produkt;

/**
* Auto-Generated DTOs
* Wed Apr 10 11:21:47 CEST 2013
*/
public class DTOBestellposition {
	private int fId;
	private int fAnzahl;
	private int fStueckpreis;
	private DTOBestellung fBestellung;
	private DTOProdukt fProdukt;

	public DTOBestellposition() {
	}

	public DTOBestellposition(Bestellposition bestellposition) {
		this();
		fId = bestellposition.getId();
		fAnzahl = bestellposition.getAnzahl();
		fStueckpreis = bestellposition.getStueckpreis();
		fBestellung = new DTOBestellung(bestellposition.getBestellung());
		fProdukt = new DTOProdukt(bestellposition.getProdukt());
	}

	public int getId() {
		return fId;
	}

	public void setId(int id) {
		fId = id;
	}

	public int getAnzahl() {
		return fAnzahl;
	}

	public void setAnzahl(int anzahl) {
		fAnzahl = anzahl;
	}

	public int getStueckpreis() {
		return fStueckpreis;
	}

	public void setStueckpreis(int stueckpreis) {
		fStueckpreis = stueckpreis;
	}

	public DTOBestellung getBestellung() {
		return fBestellung;
	}

	public void setBestellung(DTOBestellung bestellung) {
		fBestellung = bestellung;
	}

	public DTOProdukt getProdukt() {
		return fProdukt;
	}

	public void setProdukt(DTOProdukt produkt) {
		fProdukt = produkt;
	}

}