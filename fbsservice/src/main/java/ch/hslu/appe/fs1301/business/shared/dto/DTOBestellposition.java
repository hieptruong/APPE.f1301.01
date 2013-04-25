package ch.hslu.appe.fs1301.business.shared.dto;

import ch.hslu.appe.fs1301.data.shared.entity.Bestellposition;

/**
* Auto-Generated DTOs
<<<<<<< HEAD
* Thu Apr 25 13:48:57 CEST 2013
=======
* Thu Apr 25 16:16:46 CEST 2013
>>>>>>> 149fc7ad70b352e0c58b8172e77cf9880ccce8d0
*/
public class DTOBestellposition {
	private Integer fId;
	private Integer fAnzahl;
	private Integer fStueckpreis;
	private Integer fBestellung;
	private Integer fProdukt;

	public DTOBestellposition() {
	}

	public DTOBestellposition(Bestellposition bestellposition) {
		this();
		fId = bestellposition.getId();
		fAnzahl = bestellposition.getAnzahl();
		fStueckpreis = bestellposition.getStueckpreis();
		fBestellung = bestellposition.getBestellung().getId();
		fProdukt = bestellposition.getProdukt().getId();
	}

	public Integer getId() {
		return fId;
	}

	public void setId(Integer id) {
		fId = id;
	}

	public Integer getAnzahl() {
		return fAnzahl;
	}

	public void setAnzahl(Integer anzahl) {
		fAnzahl = anzahl;
	}

	public Integer getStueckpreis() {
		return fStueckpreis;
	}

	public void setStueckpreis(Integer stueckpreis) {
		fStueckpreis = stueckpreis;
	}

	public Integer getBestellung() {
		return fBestellung;
	}

	public void setBestellung(Integer bestellung) {
		fBestellung = bestellung;
	}

	public Integer getProdukt() {
		return fProdukt;
	}

	public void setProdukt(Integer produkt) {
		fProdukt = produkt;
	}

	public static Bestellposition createNewBestellpositionFromDTO(DTOBestellposition dto) {
		Bestellposition entity = new Bestellposition();
		updateBestellpositionFromDTO(entity, dto);
		return entity;
	}

	public static void updateBestellpositionFromDTO(Bestellposition entity, DTOBestellposition dto) {
		entity.setId(dto.getId() == null ? 0 : dto.getId());
		entity.setAnzahl(dto.getAnzahl() == null ? 0 : dto.getAnzahl());
		entity.setStueckpreis(dto.getStueckpreis() == null ? 0 : dto.getStueckpreis());
	}
}