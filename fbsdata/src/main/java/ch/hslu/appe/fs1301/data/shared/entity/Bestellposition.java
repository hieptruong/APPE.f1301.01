package ch.hslu.appe.fs1301.data.shared.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Bestellposition database table.
 * 
 */
@Entity
@Table(name="Bestellposition")
public class Bestellposition extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private boolean abgerechnet;
	private int anzahl;
	private int stueckpreis;
	private Bestellung bestellung;
	private Produkt produkt;

	public Bestellposition() {
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


	@Column(nullable=false)
	public boolean getAbgerechnet() {
		return this.abgerechnet;
	}

	public void setAbgerechnet(boolean abgerechnet) {
		this.abgerechnet = abgerechnet;
	}


	@Column(nullable=false)
	public int getAnzahl() {
		return this.anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}


	@Column(nullable=false)
	public int getStueckpreis() {
		return this.stueckpreis;
	}

	public void setStueckpreis(int stueckpreis) {
		this.stueckpreis = stueckpreis;
	}


	//bi-directional many-to-one association to Bestellung
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Bestellung_ID", nullable=false)
	public Bestellung getBestellung() {
		return this.bestellung;
	}

	public void setBestellung(Bestellung bestellung) {
		this.bestellung = bestellung;
	}


	//bi-directional many-to-one association to Produkt
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Produkt_ID", nullable=false)
	public Produkt getProdukt() {
		return this.produkt;
	}

	public void setProdukt(Produkt produkt) {
		this.produkt = produkt;
	}

}