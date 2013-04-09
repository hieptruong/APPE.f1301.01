package ch.hslu.appe.fs1301.business.dto;

import java.util.Date;
import java.util.List;

/**
* Auto-Generated DTOs
* Tue Apr 09 13:33:55 CEST 2013
*/
public class DTORechnung {
	private int id;
	private int betrag;
	private int bezahlter_Betrag;
	private int mahnstufe;
	private Date zahlbarBis;
	private DTOBestellung bestellung;
	private DTOPerson person;

	public DTORechnung() {
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getBetrag() {
		return this.betrag;
	}

	public void setBetrag(int betrag) {
		this.betrag = betrag;
	}


	public int getBezahlter_Betrag() {
		return this.bezahlter_Betrag;
	}

	public void setBezahlter_Betrag(int bezahlter_Betrag) {
		this.bezahlter_Betrag = bezahlter_Betrag;
	}


	public int getMahnstufe() {
		return this.mahnstufe;
	}

	public void setMahnstufe(int mahnstufe) {
		this.mahnstufe = mahnstufe;
	}


	public Date getZahlbarBis() {
		return this.zahlbarBis;
	}

	public void setZahlbarBis(Date zahlbarBis) {
		this.zahlbarBis = zahlbarBis;
	}


	public DTOBestellung getBestellung() {
		return this.bestellung;
	}

	public void setBestellung(DTOBestellung bestellung) {
		this.bestellung = bestellung;
	}


	public DTOPerson getPerson() {
		return this.person;
	}

	public void setPerson(DTOPerson person) {
		this.person = person;
	}

}
