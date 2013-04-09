package ch.hslu.appe.fs1301.business.dto;

import java.util.Date;
import java.util.List;

/**
* Auto-Generated DTOs
* Tue Apr 09 13:33:55 CEST 2013
*/
public class DTOBestellung {
	private int id;
	private Date bestelldatum;
	private Date liefertermin_Ist;
	private Date liefertermin_Soll;
	private int quelle;
	private List<DTOBestellposition> bestellpositions;
	private DTOPerson person1;
	private DTOPerson person2;
	private List<DTORechnung> rechnungs;

	public DTOBestellung() {
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Date getBestelldatum() {
		return this.bestelldatum;
	}

	public void setBestelldatum(Date bestelldatum) {
		this.bestelldatum = bestelldatum;
	}


	public Date getLiefertermin_Ist() {
		return this.liefertermin_Ist;
	}

	public void setLiefertermin_Ist(Date liefertermin_Ist) {
		this.liefertermin_Ist = liefertermin_Ist;
	}


	public Date getLiefertermin_Soll() {
		return this.liefertermin_Soll;
	}

	public void setLiefertermin_Soll(Date liefertermin_Soll) {
		this.liefertermin_Soll = liefertermin_Soll;
	}


	public int getQuelle() {
		return this.quelle;
	}

	public void setQuelle(int quelle) {
		this.quelle = quelle;
	}


	public List<DTOBestellposition> getBestellpositions() {
		return this.bestellpositions;
	}

	public void setBestellpositions(List<DTOBestellposition> bestellpositions) {
		this.bestellpositions = bestellpositions;
	}

	public DTOBestellposition addBestellposition(DTOBestellposition bestellposition) {
		getBestellpositions().add(bestellposition);
		bestellposition.setBestellung(this);

		return bestellposition;
	}

	public DTOBestellposition removeBestellposition(DTOBestellposition bestellposition) {
		getBestellpositions().remove(bestellposition);
		bestellposition.setBestellung(null);

		return bestellposition;
	}


	public DTOPerson getPerson1() {
		return this.person1;
	}

	public void setPerson1(DTOPerson person1) {
		this.person1 = person1;
	}


	public DTOPerson getPerson2() {
		return this.person2;
	}

	public void setPerson2(DTOPerson person2) {
		this.person2 = person2;
	}


	public List<DTORechnung> getRechnungs() {
		return this.rechnungs;
	}

	public void setRechnungs(List<DTORechnung> rechnungs) {
		this.rechnungs = rechnungs;
	}

	public DTORechnung addRechnung(DTORechnung rechnung) {
		getRechnungs().add(rechnung);
		rechnung.setBestellung(this);

		return rechnung;
	}

	public DTORechnung removeRechnung(DTORechnung rechnung) {
		getRechnungs().remove(rechnung);
		rechnung.setBestellung(null);

		return rechnung;
	}

}
