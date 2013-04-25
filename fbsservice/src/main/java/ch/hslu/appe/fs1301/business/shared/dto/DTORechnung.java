package ch.hslu.appe.fs1301.business.shared.dto;

import java.util.Date;
import ch.hslu.appe.fs1301.data.shared.entity.Rechnung;

/**
* Auto-Generated DTOs
<<<<<<< HEAD
* Thu Apr 25 13:48:57 CEST 2013
=======
* Thu Apr 25 16:16:46 CEST 2013
>>>>>>> 149fc7ad70b352e0c58b8172e77cf9880ccce8d0
*/
public class DTORechnung {
	private Integer fId;
	private Integer fBetrag;
	private Integer fBezahlter_Betrag;
	private Integer fMahnstufe;
	private Date fZahlbarBis;
	private Integer fBestellung;
	private Integer fPerson;

	public DTORechnung() {
	}

	public DTORechnung(Rechnung rechnung) {
		this();
		fId = rechnung.getId();
		fBetrag = rechnung.getBetrag();
		fBezahlter_Betrag = rechnung.getBezahlter_Betrag();
		fMahnstufe = rechnung.getMahnstufe();
		fZahlbarBis = rechnung.getZahlbarBis();
		fBestellung = rechnung.getBestellung().getId();
		fPerson = rechnung.getPerson().getId();
	}

	public Integer getId() {
		return fId;
	}

	public void setId(Integer id) {
		fId = id;
	}

	public Integer getBetrag() {
		return fBetrag;
	}

	public void setBetrag(Integer betrag) {
		fBetrag = betrag;
	}

	public Integer getBezahlter_Betrag() {
		return fBezahlter_Betrag;
	}

	public void setBezahlter_Betrag(Integer bezahlter_Betrag) {
		fBezahlter_Betrag = bezahlter_Betrag;
	}

	public Integer getMahnstufe() {
		return fMahnstufe;
	}

	public void setMahnstufe(Integer mahnstufe) {
		fMahnstufe = mahnstufe;
	}

	public Date getZahlbarBis() {
		return fZahlbarBis;
	}

	public void setZahlbarBis(Date zahlbarBis) {
		fZahlbarBis = zahlbarBis;
	}

	public Integer getBestellung() {
		return fBestellung;
	}

	public void setBestellung(Integer bestellung) {
		fBestellung = bestellung;
	}

	public Integer getPerson() {
		return fPerson;
	}

	public void setPerson(Integer person) {
		fPerson = person;
	}

	public static Rechnung createNewRechnungFromDTO(DTORechnung dto) {
		Rechnung entity = new Rechnung();
		updateRechnungFromDTO(entity, dto);
		return entity;
	}

	public static void updateRechnungFromDTO(Rechnung entity, DTORechnung dto) {
		entity.setId(dto.getId() == null ? 0 : dto.getId());
		entity.setBetrag(dto.getBetrag() == null ? 0 : dto.getBetrag());
		entity.setBezahlter_Betrag(dto.getBezahlter_Betrag() == null ? 0 : dto.getBezahlter_Betrag());
		entity.setMahnstufe(dto.getMahnstufe() == null ? 0 : dto.getMahnstufe());
		entity.setZahlbarBis(dto.getZahlbarBis());
	}
}