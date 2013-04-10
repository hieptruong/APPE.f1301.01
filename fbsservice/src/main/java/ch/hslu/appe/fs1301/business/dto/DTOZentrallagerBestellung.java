package ch.hslu.appe.fs1301.business.dto;

import java.util.Date;
import ch.hslu.appe.fs1301.data.shared.ZentrallagerBestellung;
import ch.hslu.appe.fs1301.data.shared.Produkt;

/**
* Auto-Generated DTOs
* Wed Apr 10 10:39:53 CEST 2013
*/
public class DTOZentrallagerBestellung {
	private int fId;
	private int fAnzahl;
	private Date fLiefertermin;
	private DTOProdukt fProdukt;

	public DTOZentrallagerBestellung() {
		
	}

	public DTOZentrallagerBestellung(ZentrallagerBestellung zentrallagerbestellung) {
		fId = zentrallagerbestellung.getId();
		fAnzahl = zentrallagerbestellung.getAnzahl();
		fLiefertermin = zentrallagerbestellung.getLiefertermin();
		fProdukt = new DTOProdukt(zentrallagerbestellung.getProdukt());
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