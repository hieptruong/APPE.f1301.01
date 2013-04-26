package ch.hslu.appe.fs1301.business.shared.dto;

import java.util.List;
import java.util.ArrayList;
import ch.hslu.appe.fs1301.data.shared.entity.Produkt;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellposition;
import ch.hslu.appe.fs1301.data.shared.entity.ZentrallagerBestellung;

/**
* Auto-Generated DTOs
* Thu Apr 25 17:36:49 CEST 2013
*/
public class DTOProdukt {
	private Integer fId;
	private String fBezeichnung;
	private Integer fLagerbestand;
	private Integer fMinimalMenge;
	private Integer fPreis;
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

	public Integer getId() {
		return fId;
	}

	public void setId(Integer id) {
		fId = id;
	}

	public String getBezeichnung() {
		return fBezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		fBezeichnung = bezeichnung;
	}

	public Integer getLagerbestand() {
		return fLagerbestand;
	}

	public void setLagerbestand(Integer lagerbestand) {
		fLagerbestand = lagerbestand;
	}

	public Integer getMinimalMenge() {
		return fMinimalMenge;
	}

	public void setMinimalMenge(Integer minimalMenge) {
		fMinimalMenge = minimalMenge;
	}

	public Integer getPreis() {
		return fPreis;
	}

	public void setPreis(Integer preis) {
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
		entity.setId(dto.getId() == null ? 0 : dto.getId());
		entity.setBezeichnung(dto.getBezeichnung());
		entity.setLagerbestand(dto.getLagerbestand() == null ? 0 : dto.getLagerbestand());
		entity.setMinimalMenge(dto.getMinimalMenge() == null ? 0 : dto.getMinimalMenge());
		entity.setPreis(dto.getPreis() == null ? 0 : dto.getPreis());
	}
}