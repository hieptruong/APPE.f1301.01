package ch.hslu.appe.fs1301.business.dto;

import java.util.Date;
import java.util.List;

/**
* Auto-Generated DTOs
* Tue Apr 09 13:33:55 CEST 2013
*/
public class DTOPerson {
	private int id;
	private boolean aktiv;
	private String benutzername;
	private String EMail;
	private Date geburtstag;
	private String name;
	private String ort;
	private String passwort;
	private int plz;
	private int rolle;
	private String strasse;
	private String vorname;
	private List<DTOBestellung> bestellungs1;
	private List<DTOBestellung> bestellungs2;
	private List<DTOKorrespondenz> korrespondenzs1;
	private List<DTOKorrespondenz> korrespondenzs2;
	private List<DTORechnung> rechnungs;

	public DTOPerson() {
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public boolean getAktiv() {
		return this.aktiv;
	}

	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}


	public String getBenutzername() {
		return this.benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}


	public String getEMail() {
		return this.EMail;
	}

	public void setEMail(String EMail) {
		this.EMail = EMail;
	}


	public Date getGeburtstag() {
		return this.geburtstag;
	}

	public void setGeburtstag(Date geburtstag) {
		this.geburtstag = geburtstag;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getOrt() {
		return this.ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}


	public String getPasswort() {
		return this.passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}


	public int getPlz() {
		return this.plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}


	public int getRolle() {
		return this.rolle;
	}

	public void setRolle(int rolle) {
		this.rolle = rolle;
	}


	public String getStrasse() {
		return this.strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}


	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}


	public List<DTOBestellung> getBestellungs1() {
		return this.bestellungs1;
	}

	public void setBestellungs1(List<DTOBestellung> bestellungs1) {
		this.bestellungs1 = bestellungs1;
	}

	public DTOBestellung addBestellungs1(DTOBestellung bestellungs1) {
		getBestellungs1().add(bestellungs1);
		bestellungs1.setPerson1(this);

		return bestellungs1;
	}

	public DTOBestellung removeBestellungs1(DTOBestellung bestellungs1) {
		getBestellungs1().remove(bestellungs1);
		bestellungs1.setPerson1(null);

		return bestellungs1;
	}


	public List<DTOBestellung> getBestellungs2() {
		return this.bestellungs2;
	}

	public void setBestellungs2(List<DTOBestellung> bestellungs2) {
		this.bestellungs2 = bestellungs2;
	}

	public DTOBestellung addBestellungs2(DTOBestellung bestellungs2) {
		getBestellungs2().add(bestellungs2);
		bestellungs2.setPerson2(this);

		return bestellungs2;
	}

	public DTOBestellung removeBestellungs2(DTOBestellung bestellungs2) {
		getBestellungs2().remove(bestellungs2);
		bestellungs2.setPerson2(null);

		return bestellungs2;
	}


	public List<DTOKorrespondenz> getKorrespondenzs1() {
		return this.korrespondenzs1;
	}

	public void setKorrespondenzs1(List<DTOKorrespondenz> korrespondenzs1) {
		this.korrespondenzs1 = korrespondenzs1;
	}

	public DTOKorrespondenz addKorrespondenzs1(DTOKorrespondenz korrespondenzs1) {
		getKorrespondenzs1().add(korrespondenzs1);
		korrespondenzs1.setPerson1(this);

		return korrespondenzs1;
	}

	public DTOKorrespondenz removeKorrespondenzs1(DTOKorrespondenz korrespondenzs1) {
		getKorrespondenzs1().remove(korrespondenzs1);
		korrespondenzs1.setPerson1(null);

		return korrespondenzs1;
	}


	public List<DTOKorrespondenz> getKorrespondenzs2() {
		return this.korrespondenzs2;
	}

	public void setKorrespondenzs2(List<DTOKorrespondenz> korrespondenzs2) {
		this.korrespondenzs2 = korrespondenzs2;
	}

	public DTOKorrespondenz addKorrespondenzs2(DTOKorrespondenz korrespondenzs2) {
		getKorrespondenzs2().add(korrespondenzs2);
		korrespondenzs2.setPerson2(this);

		return korrespondenzs2;
	}

	public DTOKorrespondenz removeKorrespondenzs2(DTOKorrespondenz korrespondenzs2) {
		getKorrespondenzs2().remove(korrespondenzs2);
		korrespondenzs2.setPerson2(null);

		return korrespondenzs2;
	}


	public List<DTORechnung> getRechnungs() {
		return this.rechnungs;
	}

	public void setRechnungs(List<DTORechnung> rechnungs) {
		this.rechnungs = rechnungs;
	}

	public DTORechnung addRechnung(DTORechnung rechnung) {
		getRechnungs().add(rechnung);
		rechnung.setPerson(this);

		return rechnung;
	}

	public DTORechnung removeRechnung(DTORechnung rechnung) {
		getRechnungs().remove(rechnung);
		rechnung.setPerson(null);

		return rechnung;
	}

}
