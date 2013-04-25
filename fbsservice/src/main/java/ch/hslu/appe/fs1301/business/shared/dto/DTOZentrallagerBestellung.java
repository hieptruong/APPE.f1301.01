package ch.hslu.appe.fs1301.business.shared.dto;

import java.util.Date;
import ch.hslu.appe.fs1301.data.shared.entity.ZentrallagerBestellung;

/**
* Auto-Generated DTOs
<<<<<<< HEAD
* Thu Apr 25 13:48:57 CEST 2013
=======
* Thu Apr 25 16:16:46 CEST 2013
>>>>>>> 149fc7ad70b352e0c58b8172e77cf9880ccce8d0
*/
public class DTOZentrallagerBestellung {
	private Integer fId;
	private Integer fAnzahl;
	private Date fLiefertermin;
	private Integer fProdukt;

	public DTOZentrallagerBestellung() {
	}

	public DTOZentrallagerBestellung(ZentrallagerBestellung zentrallagerbestellung) {
		this();
		fId = zentrallagerbestellung.getId();
		fAnzahl = zentrallagerbestellung.getAnzahl();
		fLiefertermin = zentrallagerbestellung.getLiefertermin();
		fProdukt = zentrallagerbestellung.getProdukt().getId();
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

	public Date getLiefertermin() {
		return fLiefertermin;
	}

	public void setLiefertermin(Date liefertermin) {
		fLiefertermin = liefertermin;
	}

	public Integer getProdukt() {
		return fProdukt;
	}

	public void setProdukt(Integer produkt) {
		fProdukt = produkt;
	}

	public static ZentrallagerBestellung createNewZentrallagerBestellungFromDTO(DTOZentrallagerBestellung dto) {
		ZentrallagerBestellung entity = new ZentrallagerBestellung();
		updateZentrallagerBestellungFromDTO(entity, dto);
		return entity;
	}

	public static void updateZentrallagerBestellungFromDTO(ZentrallagerBestellung entity, DTOZentrallagerBestellung dto) {
		entity.setId(dto.getId() == null ? 0 : dto.getId());
		entity.setAnzahl(dto.getAnzahl() == null ? 0 : dto.getAnzahl());
		entity.setLiefertermin(dto.getLiefertermin());
	}
}