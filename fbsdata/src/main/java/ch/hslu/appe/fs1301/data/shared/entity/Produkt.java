package ch.hslu.appe.fs1301.data.shared.entity;

import java.io.Serializable;
import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the Produkt database table.
 * 
 */
@Entity
@Table(name="Produkt")
public class Produkt extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String bezeichnung;
	private int lagerbestand;
	private int minimalMenge;
	private int preis;
	private List<Bestellposition> bestellpositions;
	private List<ZentrallagerBestellung> zentrallagerBestellungs;

	public Produkt() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Column(nullable=false, length=1000)
	public String getBezeichnung() {
		return this.bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}


	@Column(nullable=false)
	public int getLagerbestand() {
		return this.lagerbestand;
	}

	public void setLagerbestand(int lagerbestand) {
		this.lagerbestand = lagerbestand;
	}


	@Column(nullable=false)
	public int getMinimalMenge() {
		return this.minimalMenge;
	}

	public void setMinimalMenge(int minimalMenge) {
		this.minimalMenge = minimalMenge;
	}


	@Column(nullable=false)
	public int getPreis() {
		return this.preis;
	}

	public void setPreis(int preis) {
		this.preis = preis;
	}


	//bi-directional many-to-one association to Bestellposition
	@OneToMany(mappedBy="produkt")
	public List<Bestellposition> getBestellpositions() {
		return this.bestellpositions;
	}

	public void setBestellpositions(List<Bestellposition> bestellpositions) {
		this.bestellpositions = bestellpositions;
	}

	public Bestellposition addBestellposition(Bestellposition bestellposition) {
		getBestellpositions().add(bestellposition);
		bestellposition.setProdukt(this);

		return bestellposition;
	}

	public Bestellposition removeBestellposition(Bestellposition bestellposition) {
		getBestellpositions().remove(bestellposition);
		bestellposition.setProdukt(null);

		return bestellposition;
	}


	//bi-directional many-to-one association to ZentrallagerBestellung
	@OneToMany(mappedBy="produkt")
	public List<ZentrallagerBestellung> getZentrallagerBestellungs() {
		return this.zentrallagerBestellungs;
	}

	public void setZentrallagerBestellungs(List<ZentrallagerBestellung> zentrallagerBestellungs) {
		this.zentrallagerBestellungs = zentrallagerBestellungs;
	}

	public ZentrallagerBestellung addZentrallagerBestellung(ZentrallagerBestellung zentrallagerBestellung) {
		getZentrallagerBestellungs().add(zentrallagerBestellung);
		zentrallagerBestellung.setProdukt(this);

		return zentrallagerBestellung;
	}

	public ZentrallagerBestellung removeZentrallagerBestellung(ZentrallagerBestellung zentrallagerBestellung) {
		getZentrallagerBestellungs().remove(zentrallagerBestellung);
		zentrallagerBestellung.setProdukt(null);

		return zentrallagerBestellung;
	}

}