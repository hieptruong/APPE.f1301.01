package ch.hslu.appe.fs1301.business.dto;

import ch.hslu.appe.fs1301.data.shared.Korrespondenz;
import ch.hslu.appe.fs1301.data.shared.Person;

/**
* Auto-Generated DTOs
* Wed Apr 10 10:39:53 CEST 2013
*/
public class DTOKorrespondenz {
	private int fId;
	private String fInhalt;
	private int fTyp;
	private DTOPerson fPerson1;
	private DTOPerson fPerson2;

	public DTOKorrespondenz() {
		
	}

	public DTOKorrespondenz(Korrespondenz korrespondenz) {
		fId = korrespondenz.getId();
		fInhalt = korrespondenz.getInhalt();
		fTyp = korrespondenz.getTyp();
		fPerson1 = new DTOPerson(korrespondenz.getPerson1());
		fPerson2 = new DTOPerson(korrespondenz.getPerson2());
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

}