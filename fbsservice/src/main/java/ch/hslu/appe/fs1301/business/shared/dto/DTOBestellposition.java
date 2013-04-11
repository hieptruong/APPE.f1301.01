package ch.hslu.appe.fs1301.business.dto;

import ch.hslu.appe.fs1301.data.shared.entityBestellposition;

/**
* Auto-Generated DTOs
* Thu Apr 11 13:59:53 CEST 2013
*/
public class DTOBestellposition {
	private int fId;
	private int fAnzahl;
	private int fStueckpreis;
	private int fBestellung;
	private int fProdukt;

	public DTOBestellposition() {
	}

	public DTOBestellposition(Bestellposition bestellposition) {
		this();
		fId = bestellposition.getId();
		fAnzahl = bestellposition.getAnzahl();
		fStueckpreis = bestellposition.getStueckpreis();
		fBestellung = bestellposition.getBestellung().getId();
		fProdukt = bestellposition.getProdukt().getId();
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

	public int getBestellung() {
		return fBestellung;
	}

	public void setBestellung(int bestellung) {
		fBestellung = bestellung;
	}

	public int getProdukt() {
		return fProdukt;
	}

	public void setProdukt(int produkt) {
		fProdukt = produkt;
	}

}