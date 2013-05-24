package ch.hslu.appe.fs1301.data.shared.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ZentrallagerBestellung database table.
 * 
 */
@Entity
@Table(name="ZentrallagerBestellung")
public class ZentrallagerBestellung extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int anzahl;
	private Date liefertermin;
	private Produkt produkt;

	public ZentrallagerBestellung() {
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
	public int getAnzahl() {
		return this.anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getLiefertermin() {
		return this.liefertermin;
	}

	public void setLiefertermin(Date liefertermin) {
		this.liefertermin = liefertermin;
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