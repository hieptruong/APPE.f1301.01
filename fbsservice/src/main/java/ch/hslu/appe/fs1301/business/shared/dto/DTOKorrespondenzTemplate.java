package ch.hslu.appe.fs1301.business.shared.dto;

import ch.hslu.appe.fs1301.data.shared.entity.KorrespondenzTemplate;

/**
* Auto-Generated DTOs
* Thu Apr 11 23:59:20 CEST 2013
*/
public class DTOKorrespondenzTemplate {
	private int fId;
	private String fInhalt;
	private int fTyp;

	public DTOKorrespondenzTemplate() {
	}

	public DTOKorrespondenzTemplate(KorrespondenzTemplate korrespondenztemplate) {
		this();
		fId = korrespondenztemplate.getId();
		fInhalt = korrespondenztemplate.getInhalt();
		fTyp = korrespondenztemplate.getTyp();
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

}