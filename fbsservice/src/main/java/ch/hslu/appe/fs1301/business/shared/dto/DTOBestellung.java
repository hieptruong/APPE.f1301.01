package ch.hslu.appe.fs1301.business.shared.dto;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellung;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellposition;
import ch.hslu.appe.fs1301.data.shared.entity.Rechnung;

/**
* Auto-Generated DTOs
<<<<<<< HEAD
* Thu Apr 25 13:48:57 CEST 2013
=======
* Thu Apr 25 16:16:46 CEST 2013
>>>>>>> 149fc7ad70b352e0c58b8172e77cf9880ccce8d0
*/
public class DTOBestellung {
	private Integer fId;
	private Date fBestelldatum;
	private Date fLiefertermin_Ist;
	private Date fLiefertermin_Soll;
	private Integer fQuelle;
	private List<Integer> fBestellpositions;
	private Integer fPerson1;
	private Integer fPerson2;
	private List<Integer> fRechnungs;

	public DTOBestellung() {
		fBestellpositions = new ArrayList<Integer>();
		fRechnungs = new ArrayList<Integer>();
	}

	public DTOBestellung(Bestellung bestellung) {
		this();
		fId = bestellung.getId();
		fBestelldatum = bestellung.getBestelldatum();
		fLiefertermin_Ist = bestellung.getLiefertermin_Ist();
		fLiefertermin_Soll = bestellung.getLiefertermin_Soll();
		fQuelle = bestellung.getQuelle();
		for (Bestellposition bestellposition : bestellung.getBestellpositions()) {
			fBestellpositions.add(bestellposition.getId());
		}
		fPerson1 = bestellung.getPerson1().getId();
		fPerson2 = bestellung.getPerson2().getId();
		for (Rechnung rechnung : bestellung.getRechnungs()) {
			fRechnungs.add(rechnung.getId());
		}
	}

	public Integer getId() {
		return fId;
	}

	public void setId(Integer id) {
		fId = id;
	}

	public Date getBestelldatum() {
		return fBestelldatum;
	}

	public void setBestelldatum(Date bestelldatum) {
		fBestelldatum = bestelldatum;
	}

	public Date getLiefertermin_Ist() {
		return fLiefertermin_Ist;
	}

	public void setLiefertermin_Ist(Date liefertermin_Ist) {
		fLiefertermin_Ist = liefertermin_Ist;
	}

	public Date getLiefertermin_Soll() {
		return fLiefertermin_Soll;
	}

	public void setLiefertermin_Soll(Date liefertermin_Soll) {
		fLiefertermin_Soll = liefertermin_Soll;
	}

	public Integer getQuelle() {
		return fQuelle;
	}

	public void setQuelle(Integer quelle) {
		fQuelle = quelle;
	}

	public List<Integer> getBestellpositions() {
		return fBestellpositions;
	}

	public void setBestellpositions(List<Integer> bestellpositions) {
		fBestellpositions = bestellpositions;
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

	public List<Integer> getRechnungs() {
		return fRechnungs;
	}

	public void setRechnungs(List<Integer> rechnungs) {
		fRechnungs = rechnungs;
	}

	public static Bestellung createNewBestellungFromDTO(DTOBestellung dto) {
		Bestellung entity = new Bestellung();
		updateBestellungFromDTO(entity, dto);
		return entity;
	}

	public static void updateBestellungFromDTO(Bestellung entity, DTOBestellung dto) {
		entity.setId(dto.getId() == null ? 0 : dto.getId());
		entity.setBestelldatum(dto.getBestelldatum());
		entity.setLiefertermin_Ist(dto.getLiefertermin_Ist());
		entity.setLiefertermin_Soll(dto.getLiefertermin_Soll());
		entity.setQuelle(dto.getQuelle() == null ? 0 : dto.getQuelle());
	}
}