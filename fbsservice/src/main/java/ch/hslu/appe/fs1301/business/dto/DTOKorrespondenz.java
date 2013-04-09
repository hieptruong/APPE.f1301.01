package ch.hslu.appe.fs1301.business.dto;

import java.util.Date;
import java.util.List;

/**
* Auto-Generated DTOs
* Tue Apr 09 13:33:55 CEST 2013
*/
public class DTOKorrespondenz {
	private int id;
	private String inhalt;
	private int typ;
	private DTOPerson person1;
	private DTOPerson person2;

	public DTOKorrespondenz() {
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getInhalt() {
		return this.inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}


	public int getTyp() {
		return this.typ;
	}

	public void setTyp(int typ) {
		this.typ = typ;
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

}
