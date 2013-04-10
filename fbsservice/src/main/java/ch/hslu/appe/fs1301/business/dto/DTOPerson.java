package ch.hslu.appe.fs1301.business.dto;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import ch.hslu.appe.fs1301.data.shared.Person;
import ch.hslu.appe.fs1301.data.shared.Bestellung;
import ch.hslu.appe.fs1301.data.shared.Korrespondenz;
import ch.hslu.appe.fs1301.data.shared.Rechnung;

/**
* Auto-Generated DTOs
* Wed Apr 10 11:21:47 CEST 2013
*/
public class DTOPerson {
	private int fId;
	private boolean fAktiv;
	private String fBenutzername;
	private String fEMail;
	private Date fGeburtstag;
	private String fName;
	private String fOrt;
	private String fPasswort;
	private int fPlz;
	private int fRolle;
	private String fStrasse;
	private String fVorname;
	private List<DTOBestellung> fBestellungs1;
	private List<DTOBestellung> fBestellungs2;
	private List<DTOKorrespondenz> fKorrespondenzs1;
	private List<DTOKorrespondenz> fKorrespondenzs2;
	private List<DTORechnung> fRechnungs;

	public DTOPerson() {
		fBestellungs1 = new ArrayList<DTOBestellung>();
		fBestellungs2 = new ArrayList<DTOBestellung>();
		fKorrespondenzs1 = new ArrayList<DTOKorrespondenz>();
		fKorrespondenzs2 = new ArrayList<DTOKorrespondenz>();
		fRechnungs = new ArrayList<DTORechnung>();
	}

	public DTOPerson(Person person) {
		this();
		fId = person.getId();
		fAktiv = person.getAktiv();
		fBenutzername = person.getBenutzername();
		fEMail = person.getEMail();
		fGeburtstag = person.getGeburtstag();
		fName = person.getName();
		fOrt = person.getOrt();
		fPasswort = person.getPasswort();
		fPlz = person.getPlz();
		fRolle = person.getRolle();
		fStrasse = person.getStrasse();
		fVorname = person.getVorname();
		for (Bestellung bestellung : person.getBestellungs1()) {
			fBestellungs1.add(new DTOBestellung(bestellung));
		}
		for (Bestellung bestellung : person.getBestellungs2()) {
			fBestellungs2.add(new DTOBestellung(bestellung));
		}
		for (Korrespondenz korrespondenz : person.getKorrespondenzs1()) {
			fKorrespondenzs1.add(new DTOKorrespondenz(korrespondenz));
		}
		for (Korrespondenz korrespondenz : person.getKorrespondenzs2()) {
			fKorrespondenzs2.add(new DTOKorrespondenz(korrespondenz));
		}
		for (Rechnung rechnung : person.getRechnungs()) {
			fRechnungs.add(new DTORechnung(rechnung));
		}
	}

	public int getId() {
		return fId;
	}

	public void setId(int id) {
		fId = id;
	}

	public boolean getAktiv() {
		return fAktiv;
	}

	public void setAktiv(boolean aktiv) {
		fAktiv = aktiv;
	}

	public String getBenutzername() {
		return fBenutzername;
	}

	public void setBenutzername(String benutzername) {
		fBenutzername = benutzername;
	}

	public String getEMail() {
		return fEMail;
	}

	public void setEMail(String EMail) {
		fEMail = EMail;
	}

	public Date getGeburtstag() {
		return fGeburtstag;
	}

	public void setGeburtstag(Date geburtstag) {
		fGeburtstag = geburtstag;
	}

	public String getName() {
		return fName;
	}

	public void setName(String name) {
		fName = name;
	}

	public String getOrt() {
		return fOrt;
	}

	public void setOrt(String ort) {
		fOrt = ort;
	}

	public String getPasswort() {
		return fPasswort;
	}

	public void setPasswort(String passwort) {
		fPasswort = passwort;
	}

	public int getPlz() {
		return fPlz;
	}

	public void setPlz(int plz) {
		fPlz = plz;
	}

	public int getRolle() {
		return fRolle;
	}

	public void setRolle(int rolle) {
		fRolle = rolle;
	}

	public String getStrasse() {
		return fStrasse;
	}

	public void setStrasse(String strasse) {
		fStrasse = strasse;
	}

	public String getVorname() {
		return fVorname;
	}

	public void setVorname(String vorname) {
		fVorname = vorname;
	}

	public List<DTOBestellung> getBestellungs1() {
		return fBestellungs1;
	}

	public void setBestellungs1(List<DTOBestellung> bestellungs1) {
		fBestellungs1 = bestellungs1;
	}

	public List<DTOBestellung> getBestellungs2() {
		return fBestellungs2;
	}

	public void setBestellungs2(List<DTOBestellung> bestellungs2) {
		fBestellungs2 = bestellungs2;
	}

	public List<DTOKorrespondenz> getKorrespondenzs1() {
		return fKorrespondenzs1;
	}

	public void setKorrespondenzs1(List<DTOKorrespondenz> korrespondenzs1) {
		fKorrespondenzs1 = korrespondenzs1;
	}

	public List<DTOKorrespondenz> getKorrespondenzs2() {
		return fKorrespondenzs2;
	}

	public void setKorrespondenzs2(List<DTOKorrespondenz> korrespondenzs2) {
		fKorrespondenzs2 = korrespondenzs2;
	}

	public List<DTORechnung> getRechnungs() {
		return fRechnungs;
	}

	public void setRechnungs(List<DTORechnung> rechnungs) {
		fRechnungs = rechnungs;
	}

}