package ch.hslu.appe.fs1301.business.shared.dto;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import ch.hslu.appe.fs1301.data.shared.entity.Person;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellung;
import ch.hslu.appe.fs1301.data.shared.entity.Korrespondenz;
import ch.hslu.appe.fs1301.data.shared.entity.Rechnung;

/**
* Auto-Generated DTOs
* Thu Apr 11 14:03:18 CEST 2013
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
	private List<Integer> fBestellungs1;
	private List<Integer> fBestellungs2;
	private List<Integer> fKorrespondenzs1;
	private List<Integer> fKorrespondenzs2;
	private List<Integer> fRechnungs;

	public DTOPerson() {
		fBestellungs1 = new ArrayList<Integer>();
		fBestellungs2 = new ArrayList<Integer>();
		fKorrespondenzs1 = new ArrayList<Integer>();
		fKorrespondenzs2 = new ArrayList<Integer>();
		fRechnungs = new ArrayList<Integer>();
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
			fBestellungs1.add(bestellung.getId());
		}
		for (Bestellung bestellung : person.getBestellungs2()) {
			fBestellungs2.add(bestellung.getId());
		}
		for (Korrespondenz korrespondenz : person.getKorrespondenzs1()) {
			fKorrespondenzs1.add(korrespondenz.getId());
		}
		for (Korrespondenz korrespondenz : person.getKorrespondenzs2()) {
			fKorrespondenzs2.add(korrespondenz.getId());
		}
		for (Rechnung rechnung : person.getRechnungs()) {
			fRechnungs.add(rechnung.getId());
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

	public List<Integer> getBestellungs1() {
		return fBestellungs1;
	}

	public void setBestellungs1(List<Integer> bestellungs1) {
		fBestellungs1 = bestellungs1;
	}

	public List<Integer> getBestellungs2() {
		return fBestellungs2;
	}

	public void setBestellungs2(List<Integer> bestellungs2) {
		fBestellungs2 = bestellungs2;
	}

	public List<Integer> getKorrespondenzs1() {
		return fKorrespondenzs1;
	}

	public void setKorrespondenzs1(List<Integer> korrespondenzs1) {
		fKorrespondenzs1 = korrespondenzs1;
	}

	public List<Integer> getKorrespondenzs2() {
		return fKorrespondenzs2;
	}

	public void setKorrespondenzs2(List<Integer> korrespondenzs2) {
		fKorrespondenzs2 = korrespondenzs2;
	}

	public List<Integer> getRechnungs() {
		return fRechnungs;
	}

	public void setRechnungs(List<Integer> rechnungs) {
		fRechnungs = rechnungs;
	}

}