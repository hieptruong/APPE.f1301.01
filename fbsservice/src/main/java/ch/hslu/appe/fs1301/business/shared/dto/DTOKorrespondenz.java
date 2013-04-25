package ch.hslu.appe.fs1301.business.shared.dto;

import ch.hslu.appe.fs1301.data.shared.entity.Korrespondenz;

/**
* Auto-Generated DTOs
<<<<<<< HEAD
* Thu Apr 25 13:48:57 CEST 2013
=======
* Thu Apr 25 16:16:46 CEST 2013
>>>>>>> 149fc7ad70b352e0c58b8172e77cf9880ccce8d0
*/
public class DTOKorrespondenz {
	private Integer fId;
	private String fInhalt;
	private Integer fTyp;
	private Integer fPerson1;
	private Integer fPerson2;

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

	public Integer getId() {
		return fId;
	}

	public void setId(Integer id) {
		fId = id;
	}

	public String getInhalt() {
		return fInhalt;
	}

	public void setInhalt(String inhalt) {
		fInhalt = inhalt;
	}

	public Integer getTyp() {
		return fTyp;
	}

	public void setTyp(Integer typ) {
		fTyp = typ;
	}

	public Integer getPerson1() {
		return fPerson1;
	}

	public void setPerson1(Integer person1) {
		fPerson1 = person1;
	}

	public Integer getPerson2() {
		return fPerson2;
	}

	public void setPerson2(Integer person2) {
		fPerson2 = person2;
	}

	public static Korrespondenz createNewKorrespondenzFromDTO(DTOKorrespondenz dto) {
		Korrespondenz entity = new Korrespondenz();
		updateKorrespondenzFromDTO(entity, dto);
		return entity;
	}

	public static void updateKorrespondenzFromDTO(Korrespondenz entity, DTOKorrespondenz dto) {
		entity.setId(dto.getId() == null ? 0 : dto.getId());
		entity.setInhalt(dto.getInhalt());
		entity.setTyp(dto.getTyp() == null ? 0 : dto.getTyp());
	}
}