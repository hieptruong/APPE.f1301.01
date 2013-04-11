package ch.hslu.appe.fs1301.business.shared.dto;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellung;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellposition;
import ch.hslu.appe.fs1301.data.shared.entity.Rechnung;

/**
* Auto-Generated DTOs
* Thu Apr 11 23:59:20 CEST 2013
*/
public class DTOBestellung {
	private int fId;
	private Date fBestelldatum;
	private Date fLiefertermin_Ist;
	private Date fLiefertermin_Soll;
	private int fQuelle;
	private List<Integer> fBestellpositions;
	private int fPerson1;
	private int fPerson2;
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

	public int getId() {
		return fId;
	}

	public void setId(int id) {
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

	public int getQuelle() {
		return fQuelle;
	}

	public void setQuelle(int quelle) {
		fQuelle = quelle;
	}

	public List<Integer> getBestellpositions() {
		return fBestellpositions;
	}

	public void setBestellpositions(List<Integer> bestellpositions) {
		fBestellpositions = bestellpositions;
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

	public List<Integer> getRechnungs() {
		return fRechnungs;
	}

	public void setRechnungs(List<Integer> rechnungs) {
		fRechnungs = rechnungs;
	}

}