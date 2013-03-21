package ch.hslu.appe.fs1301.data.shared;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Bestellung database table.
 * 
 */
@Entity
@Table(name="Bestellung")
public class Bestellung extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date bestelldatum;
	private Date liefertermin_Ist;
	private Date liefertermin_Soll;
	private int quelle;
	private List<Bestellposition> bestellpositions;
	private Person person1;
	private Person person2;
	private List<Rechnung> rechnungs;

	public Bestellung() {
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


	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getBestelldatum() {
		return this.bestelldatum;
	}

	public void setBestelldatum(Date bestelldatum) {
		this.bestelldatum = bestelldatum;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getLiefertermin_Ist() {
		return this.liefertermin_Ist;
	}

	public void setLiefertermin_Ist(Date liefertermin_Ist) {
		this.liefertermin_Ist = liefertermin_Ist;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getLiefertermin_Soll() {
		return this.liefertermin_Soll;
	}

	public void setLiefertermin_Soll(Date liefertermin_Soll) {
		this.liefertermin_Soll = liefertermin_Soll;
	}


	@Column(nullable=false)
	public int getQuelle() {
		return this.quelle;
	}

	public void setQuelle(int quelle) {
		this.quelle = quelle;
	}


	//bi-directional many-to-one association to Bestellposition
	@OneToMany(mappedBy="bestellung")
	public List<Bestellposition> getBestellpositions() {
		return this.bestellpositions;
	}

	public void setBestellpositions(List<Bestellposition> bestellpositions) {
		this.bestellpositions = bestellpositions;
	}

	public Bestellposition addBestellposition(Bestellposition bestellposition) {
		getBestellpositions().add(bestellposition);
		bestellposition.setBestellung(this);

		return bestellposition;
	}

	public Bestellposition removeBestellposition(Bestellposition bestellposition) {
		getBestellpositions().remove(bestellposition);
		bestellposition.setBestellung(null);

		return bestellposition;
	}


	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Verkaeufer_ID", nullable=false)
	public Person getPerson1() {
		return this.person1;
	}

	public void setPerson1(Person person1) {
		this.person1 = person1;
	}


	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Kunde_ID", nullable=false)
	public Person getPerson2() {
		return this.person2;
	}

	public void setPerson2(Person person2) {
		this.person2 = person2;
	}


	//bi-directional many-to-one association to Rechnung
	@OneToMany(mappedBy="bestellung")
	public List<Rechnung> getRechnungs() {
		return this.rechnungs;
	}

	public void setRechnungs(List<Rechnung> rechnungs) {
		this.rechnungs = rechnungs;
	}

	public Rechnung addRechnung(Rechnung rechnung) {
		getRechnungs().add(rechnung);
		rechnung.setBestellung(this);

		return rechnung;
	}

	public Rechnung removeRechnung(Rechnung rechnung) {
		getRechnungs().remove(rechnung);
		rechnung.setBestellung(null);

		return rechnung;
	}

}