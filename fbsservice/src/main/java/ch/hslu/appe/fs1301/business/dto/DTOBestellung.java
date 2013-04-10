package ch.hslu.appe.fs1301.business.dto;

import java.util.Date;
import java.util.List;
import ch.hslu.appe.fs1301.data.shared.Bestellung;
import ch.hslu.appe.fs1301.data.shared.Bestellposition;
import ch.hslu.appe.fs1301.data.shared.Person;
import ch.hslu.appe.fs1301.data.shared.Rechnung;

/**
* Auto-Generated DTOs
* Wed Apr 10 10:39:53 CEST 2013
*/
public class DTOBestellung {
	private int fId;
	private Date fBestelldatum;
	private Date fLiefertermin_Ist;
	private Date fLiefertermin_Soll;
	private int fQuelle;
	private List<DTOBestellposition> fBestellpositions;
	private DTOPerson fPerson1;
	private DTOPerson fPerson2;
	private List<DTORechnung> fRechnungs;

	public DTOBestellung() {
		
	}

	public DTOBestellung(Bestellung bestellung) {
		fId = bestellung.getId();
		fBestelldatum = bestellung.getBestelldatum();
		fLiefertermin_Ist = bestellung.getLiefertermin_Ist();
		fLiefertermin_Soll = bestellung.getLiefertermin_Soll();
		fQuelle = bestellung.getQuelle();
		fPerson1 = new DTOPerson(bestellung.getPerson1());
		fPerson2 = new DTOPerson(bestellung.getPerson2());
	}

	public int getId() {
		return fId;
	}

	public void setId(int id) {
		fId = id;
	}

	public Date getBestelldatum() {
		return fBestelldatum;
	}

	public void setBestelldatum(Date bestelldatum) {
		fBestelldatum = bestelldatum;
	}

	public Date getLiefertermin_Ist() {
		return fLiefertermin_Ist;
	}

	public void setLiefertermin_Ist(Date liefertermin_Ist) {
		fLiefertermin_Ist = liefertermin_Ist;
	}

	public Date getLiefertermin_Soll() {
		return fLiefertermin_Soll;
	}

	public void setLiefertermin_Soll(Date liefertermin_Soll) {
		fLiefertermin_Soll = liefertermin_Soll;
	}

	public int getQuelle() {
		return fQuelle;
	}

	public void setQuelle(int quelle) {
		fQuelle = quelle;
	}

	public List<DTOBestellposition> getBestellpositions() {
		return fBestellpositions;
	}

	public void setBestellpositions(List<DTOBestellposition> bestellpositions) {
		fBestellpositions = bestellpositions;
	}

	public DTOPerson getPerson1() {
		return fPerson1;
	}

	public void setPerson1(DTOPerson person1) {
		fPerson1 = person1;
	}

	public DTOPerson getPerson2() {
		return fPerson2;
	}

	public void setPerson2(DTOPerson person2) {
		fPerson2 = person2;
	}

	public List<DTORechnung> getRechnungs() {
		return fRechnungs;
	}

	public void setRechnungs(List<DTORechnung> rechnungs) {
		fRechnungs = rechnungs;
	}

}