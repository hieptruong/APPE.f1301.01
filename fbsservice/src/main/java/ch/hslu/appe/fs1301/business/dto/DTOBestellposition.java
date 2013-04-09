package ch.hslu.appe.fs1301.business.dto;

import java.util.Date;
import java.util.List;

/**
* Auto-Generated DTOs
* Tue Apr 09 13:33:55 CEST 2013
*/
public class DTOBestellposition {
	private int id;
	private int anzahl;
	private int stueckpreis;
	private DTOBestellung bestellung;
	private DTOProdukt produkt;

	public DTOBestellposition() {
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getAnzahl() {
		return this.anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}


	public int getStueckpreis() {
		return this.stueckpreis;
	}

	public void setStueckpreis(int stueckpreis) {
		this.stueckpreis = stueckpreis;
	}


	public DTOBestellung getBestellung() {
		return this.bestellung;
	}

	public void setBestellung(DTOBestellung bestellung) {
		this.bestellung = bestellung;
	}


	public DTOProdukt getProdukt() {
		return this.produkt;
	}

	public void setProdukt(DTOProdukt produkt) {
		this.produkt = produkt;
	}

}
