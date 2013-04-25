package ch.hslu.appe.fs1301.business.shared.dto;

import java.util.List;
import java.util.ArrayList;
import ch.hslu.appe.fs1301.data.shared.entity.Produkt;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellposition;
import ch.hslu.appe.fs1301.data.shared.entity.ZentrallagerBestellung;

/**
* Auto-Generated DTOs
* Thu Apr 25 13:48:57 CEST 2013
*/
public class DTOProdukt {
	private int fId;
	private String fBezeichnung;
	private int fLagerbestand;
	private int fMinimalMenge;
	private int fPreis;
	private List<Integer> fBestellpositions;
	private List<Integer> fZentrallagerBestellungs;

	public DTOProdukt() {
		fBestellpositions = new ArrayList<Integer>();
		fZentrallagerBestellungs = new ArrayList<Integer>();
	}

	public DTOProdukt(Produkt produkt) {
		this();
		fId = produkt.getId();
		fBezeichnung = produkt.getBezeichnung();
		fLagerbestand = produkt.getLagerbestand();
		fMinimalMenge = produkt.getMinimalMenge();
		fPreis = produkt.getPreis();
		for (Bestellposition bestellposition : produkt.getBestellpositions()) {
			fBestellpositions.add(bestellposition.getId());
		}
		for (ZentrallagerBestellung zentrallagerbestellung : produkt.getZentrallagerBestellungs()) {
			fZentrallagerBestellungs.add(zentrallagerbestellung.getId());
		}
	}

	public int getId() {
		return fId;
	}

	public void setId(int id) {
		fId = id;
	}

	public String getBezeichnung() {
		return fBezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		fBezeichnung = bezeichnung;
	}

	public int getLagerbestand() {
		return fLagerbestand;
	}

	public void setLagerbestand(int lagerbestand) {
		fLagerbestand = lagerbestand;
	}

	public int getMinimalMenge() {
		return fMinimalMenge;
	}

	public void setMinimalMenge(int minimalMenge) {
		fMinimalMenge = minimalMenge;
	}

	public int getPreis() {
		return fPreis;
	}

	public void setPreis(int preis) {
		fPreis = preis;
	}

	public List<Integer> getBestellpositions() {
		return fBestellpositions;
	}

	public void setBestellpositions(List<Integer> bestellpositions) {
		fBestellpositions = bestellpositions;
	}

	public List<Integer> getZentrallagerBestellungs() {
		return fZentrallagerBestellungs;
	}

	public void setZentrallagerBestellungs(List<Integer> zentrallagerBestellungs) {
		fZentrallagerBestellungs = zentrallagerBestellungs;
	}

	public static Produkt createNewProduktFromDTO(DTOProdukt dto) {
		Produkt entity = new Produkt();
		updateProduktFromDTO(entity, dto);
		return entity;
	}

	public static void updateProduktFromDTO(Produkt entity, DTOProdukt dto) {
		entity.setId(dto.getId());
		entity.setBezeichnung(dto.getBezeichnung());
		entity.setLagerbestand(dto.getLagerbestand());
		entity.setMinimalMenge(dto.getMinimalMenge());
		entity.setPreis(dto.getPreis());
	}
}