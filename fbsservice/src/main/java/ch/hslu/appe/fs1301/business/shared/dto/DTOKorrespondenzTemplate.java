package ch.hslu.appe.fs1301.business.shared.dto;

import ch.hslu.appe.fs1301.data.shared.entity.KorrespondenzTemplate;

/**
* Auto-Generated DTOs
<<<<<<< HEAD
* Thu Apr 25 13:48:57 CEST 2013
=======
* Thu Apr 25 16:16:46 CEST 2013
>>>>>>> 149fc7ad70b352e0c58b8172e77cf9880ccce8d0
*/
public class DTOKorrespondenzTemplate {
	private Integer fId;
	private String fInhalt;
	private Integer fTyp;

	public DTOKorrespondenzTemplate() {
	}

	public DTOKorrespondenzTemplate(KorrespondenzTemplate korrespondenztemplate) {
		this();
		fId = korrespondenztemplate.getId();
		fInhalt = korrespondenztemplate.getInhalt();
		fTyp = korrespondenztemplate.getTyp();
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

	public static KorrespondenzTemplate createNewKorrespondenzTemplateFromDTO(DTOKorrespondenzTemplate dto) {
		KorrespondenzTemplate entity = new KorrespondenzTemplate();
		updateKorrespondenzTemplateFromDTO(entity, dto);
		return entity;
	}

	public static void updateKorrespondenzTemplateFromDTO(KorrespondenzTemplate entity, DTOKorrespondenzTemplate dto) {
		entity.setId(dto.getId() == null ? 0 : dto.getId());
		entity.setInhalt(dto.getInhalt());
		entity.setTyp(dto.getTyp() == null ? 0 : dto.getTyp());
	}
}