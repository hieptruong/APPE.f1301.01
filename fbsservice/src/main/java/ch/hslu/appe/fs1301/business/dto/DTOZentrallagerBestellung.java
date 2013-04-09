package ch.hslu.appe.fs1301.business.dto;

import java.util.Date;
import java.util.List;

/**
* Auto-Generated DTOs
* Tue Apr 09 13:33:55 CEST 2013
*/
public class DTOZentrallagerBestellung {
	private int id;
	private int anzahl;
	private Date liefertermin;
	private DTOProdukt produkt;

	public DTOZentrallagerBestellung() {
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


	public Date getLiefertermin() {
		return this.liefertermin;
	}

	public void setLiefertermin(Date liefertermin) {
		this.liefertermin = liefertermin;
	}


	public DTOProdukt getProdukt() {
		return this.produkt;
	}

	public void setProdukt(DTOProdukt produkt) {
		this.produkt = produkt;
	}

}
