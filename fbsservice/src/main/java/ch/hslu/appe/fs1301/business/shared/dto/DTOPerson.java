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
* Thu Apr 25 17:36:49 CEST 2013
*/
public class DTOPerson {
	private Integer fId;
	private boolean fAktiv;
	private String fBenutzername;
	private String fEMail;
	private Date fGeburtstag;
	private String fName;
	private String fOrt;
	private String fPasswort;
	private Integer fPlz;
	private Integer fRolle;
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

	public Integer getId() {
		return fId;
	}

	public void setId(Integer id) {
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

	public Integer getPlz() {
		return fPlz;
	}

	public void setPlz(Integer plz) {
		fPlz = plz;
	}

	public Integer getRolle() {
		return fRolle;
	}

	public void setRolle(Integer rolle) {
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

	public static Person createNewPersonFromDTO(DTOPerson dto) {
		Person entity = new Person();
		updatePersonFromDTO(entity, dto);
		return entity;
	}

	public static void updatePersonFromDTO(Person entity, DTOPerson dto) {
		entity.setId(dto.getId() == null ? 0 : dto.getId());
		entity.setAktiv(dto.getAktiv());
		entity.setBenutzername(dto.getBenutzername());
		entity.setEMail(dto.getEMail());
		entity.setGeburtstag(dto.getGeburtstag());
		entity.setName(dto.getName());
		entity.setOrt(dto.getOrt());
		entity.setPasswort(dto.getPasswort());
		entity.setPlz(dto.getPlz() == null ? 0 : dto.getPlz());
		entity.setRolle(dto.getRolle() == null ? 0 : dto.getRolle());
		entity.setStrasse(dto.getStrasse());
		entity.setVorname(dto.getVorname());
	}
}