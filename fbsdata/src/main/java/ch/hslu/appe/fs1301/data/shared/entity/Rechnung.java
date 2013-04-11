package ch.hslu.appe.fs1301.data.shared.entity;

import java.io.Serializable;
import javax.persistence.*;


import java.util.Date;


/**
 * The persistent class for the Rechnung database table.
 * 
 */
@Entity
@Table(name="Rechnung")
public class Rechnung extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int betrag;
	private int bezahlter_Betrag;
	private int mahnstufe;
	private Date zahlbarBis;
	private Bestellung bestellung;
	private Person person;

	public Rechnung() {
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
	public int getBetrag() {
		return this.betrag;
	}

	public void setBetrag(int betrag) {
		this.betrag = betrag;
	}


	@Column(nullable=false)
	public int getBezahlter_Betrag() {
		return this.bezahlter_Betrag;
	}

	public void setBezahlter_Betrag(int bezahlter_Betrag) {
		this.bezahlter_Betrag = bezahlter_Betrag;
	}


	@Column(nullable=false)
	public int getMahnstufe() {
		return this.mahnstufe;
	}

	public void setMahnstufe(int mahnstufe) {
		this.mahnstufe = mahnstufe;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getZahlbarBis() {
		return this.zahlbarBis;
	}

	public void setZahlbarBis(Date zahlbarBis) {
		this.zahlbarBis = zahlbarBis;
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


	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Person_ID", nullable=false)
	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}