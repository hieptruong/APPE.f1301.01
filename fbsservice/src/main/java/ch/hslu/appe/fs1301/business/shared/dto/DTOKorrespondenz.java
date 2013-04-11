package ch.hslu.appe.fs1301.business.shared.dto;

import ch.hslu.appe.fs1301.data.shared.entity.Korrespondenz;

/**
* Auto-Generated DTOs
* Thu Apr 11 14:03:18 CEST 2013
*/
public class DTOKorrespondenz {
	private int fId;
	private String fInhalt;
	private int fTyp;
	private int fPerson1;
	private int fPerson2;

	public DTOKorrespondenz() {
	}

	public DTOKorrespondenz(Korrespondenz korrespondenz) {
		this();
		fId = korrespondenz.getId();
		fInhalt = korrespondenz.getInhalt();
		fTyp = korrespondenz.getTyp();
		fPerson1 = korrespondenz.getPerson1().getId();
		fPerson2 = korrespondenz.getPerson2().getId();
	}

	public int getId() {
		return fId;
	}

	public void setId(int id) {
		fId = id;
	}

	public String getInhalt() {
		return fInhalt;
	}

	public void setInhalt(String inhalt) {
		fInhalt = inhalt;
	}

	public int getTyp() {
		return fTyp;
	}

	public void setTyp(int typ) {
		fTyp = typ;
	}

	public int getPerson1() {
		return fPerson1;
	}

	public void setPerson1(int person1) {
		fPerson1 = person1;
	}

	public int getPerson2() {
		return fPerson2;
	}

	public void setPerson2(int person2) {
		fPerson2 = person2;
	}

}