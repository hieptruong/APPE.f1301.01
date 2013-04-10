package ch.hslu.appe.fs1301.business.dto;

import java.util.Date;
import ch.hslu.appe.fs1301.data.shared.Rechnung;
import ch.hslu.appe.fs1301.data.shared.Bestellung;
import ch.hslu.appe.fs1301.data.shared.Person;

/**
* Auto-Generated DTOs
* Wed Apr 10 11:21:47 CEST 2013
*/
public class DTORechnung {
	private int fId;
	private int fBetrag;
	private int fBezahlter_Betrag;
	private int fMahnstufe;
	private Date fZahlbarBis;
	private DTOBestellung fBestellung;
	private DTOPerson fPerson;

	public DTORechnung() {
	}

	public DTORechnung(Rechnung rechnung) {
		this();
		fId = rechnung.getId();
		fBetrag = rechnung.getBetrag();
		fBezahlter_Betrag = rechnung.getBezahlter_Betrag();
		fMahnstufe = rechnung.getMahnstufe();
		fZahlbarBis = rechnung.getZahlbarBis();
		fBestellung = new DTOBestellung(rechnung.getBestellung());
		fPerson = new DTOPerson(rechnung.getPerson());
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

	public DTOBestellung getBestellung() {
		return fBestellung;
	}

	public void setBestellung(DTOBestellung bestellung) {
		fBestellung = bestellung;
	}

	public DTOPerson getPerson() {
		return fPerson;
	}

	public void setPerson(DTOPerson person) {
		fPerson = person;
	}

}