package ch.hslu.appe.fs1301.business.dto;

import java.util.Date;

/**
* Auto-Generated DTOs
* Wed Apr 10 09:57:59 CEST 2013
*/
public class DTOZentrallagerBestellung {
	private int fId;
	private int fAnzahl;
	private Date fLiefertermin;
	private DTOProdukt fProdukt;

	public DTOZentrallagerBestellung() {
		
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

	public Date getLiefertermin() {
		return fLiefertermin;
	}

	public void setLiefertermin(Date liefertermin) {
		fLiefertermin = liefertermin;
	}

	public DTOProdukt getProdukt() {
		return fProdukt;
	}

	public void setProdukt(DTOProdukt produkt) {
		fProdukt = produkt;
	}

}