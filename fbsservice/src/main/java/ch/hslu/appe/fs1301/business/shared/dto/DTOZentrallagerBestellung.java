package ch.hslu.appe.fs1301.business.dto;

import java.util.Date;
import ch.hslu.appe.fs1301.data.shared.entityZentrallagerBestellung;

/**
* Auto-Generated DTOs
* Thu Apr 11 13:59:53 CEST 2013
*/
public class DTOZentrallagerBestellung {
	private int fId;
	private int fAnzahl;
	private Date fLiefertermin;
	private int fProdukt;

	public DTOZentrallagerBestellung() {
	}

	public DTOZentrallagerBestellung(ZentrallagerBestellung zentrallagerbestellung) {
		this();
		fId = zentrallagerbestellung.getId();
		fAnzahl = zentrallagerbestellung.getAnzahl();
		fLiefertermin = zentrallagerbestellung.getLiefertermin();
		fProdukt = zentrallagerbestellung.getProdukt().getId();
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

	public int getProdukt() {
		return fProdukt;
	}

	public void setProdukt(int produkt) {
		fProdukt = produkt;
	}

}