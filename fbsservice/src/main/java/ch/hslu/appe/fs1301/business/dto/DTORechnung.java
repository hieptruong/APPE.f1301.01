package ch.hslu.appe.fs1301.business.dto;

import java.util.Date;
import ch.hslu.appe.fs1301.data.shared.Rechnung;

/**
* Auto-Generated DTOs
* Thu Apr 11 13:15:54 CEST 2013
*/
public class DTORechnung {
	private int fId;
	private int fBetrag;
	private int fBezahlter_Betrag;
	private int fMahnstufe;
	private Date fZahlbarBis;
	private int fBestellung;
	private int fPerson;

	public DTORechnung() {
	}

	public DTORechnung(Rechnung rechnung) {
		this();
		fId = rechnung.getId();
		fBetrag = rechnung.getBetrag();
		fBezahlter_Betrag = rechnung.getBezahlter_Betrag();
		fMahnstufe = rechnung.getMahnstufe();
		fZahlbarBis = rechnung.getZahlbarBis();
		fBestellung = rechnung.getBestellung().getId();
		fPerson = rechnung.getPerson().getId();
	}

	public int getId() {
		return fId;
	}

	public void setId(int id) {
		fId = id;
	}

	public int getBetrag() {
		return fBetrag;
	}

	public void setBetrag(int betrag) {
		fBetrag = betrag;
	}

	public int getBezahlter_Betrag() {
		return fBezahlter_Betrag;
	}

	public void setBezahlter_Betrag(int bezahlter_Betrag) {
		fBezahlter_Betrag = bezahlter_Betrag;
	}

	public int getMahnstufe() {
		return fMahnstufe;
	}

	public void setMahnstufe(int mahnstufe) {
		fMahnstufe = mahnstufe;
	}

	public Date getZahlbarBis() {
		return fZahlbarBis;
	}

	public void setZahlbarBis(Date zahlbarBis) {
		fZahlbarBis = zahlbarBis;
	}

	public int getBestellung() {
		return fBestellung;
	}

	public void setBestellung(int bestellung) {
		fBestellung = bestellung;
	}

	public int getPerson() {
		return fPerson;
	}

	public void setPerson(int person) {
		fPerson = person;
	}

}