package ch.hslu.appe.fs1301.data.shared;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Person database table.
 * 
 */
@Entity
@Table(name="Person")
public class Person extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private boolean aktiv;
	private String benutzername;
	private String EMail;
	private Date geburtstag;
	private String name;
	private String ort;
	private String passwort;
	private int plz;
	private int rolle;
	private String strasse;
	private String vorname;
	private List<Bestellung> bestellungs1;
	private List<Bestellung> bestellungs2;
	private List<Korrespondenz> korrespondenzs1;
	private List<Korrespondenz> korrespondenzs2;
	private List<Rechnung> rechnungs;

	public Person() {
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


	public boolean getAktiv() {
		return this.aktiv;
	}

	public void setAktiv(boolean aktiv) {
		this.aktiv = aktiv;
	}


	@Column(nullable=false, length=1000)
	public String getBenutzername() {
		return this.benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}


	@Column(nullable=false, length=1000)
	public String getEMail() {
		return this.EMail;
	}

	public void setEMail(String EMail) {
		this.EMail = EMail;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	public Date getGeburtstag() {
		return this.geburtstag;
	}

	public void setGeburtstag(Date geburtstag) {
		this.geburtstag = geburtstag;
	}


	@Column(nullable=false, length=1000)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(nullable=false, length=1000)
	public String getOrt() {
		return this.ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}


	@Column(nullable=false, length=1000)
	public String getPasswort() {
		return this.passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}


	@Column(nullable=false)
	public int getPlz() {
		return this.plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}


	@Column(nullable=false)
	public int getRolle() {
		return this.rolle;
	}

	public void setRolle(int rolle) {
		this.rolle = rolle;
	}


	@Column(nullable=false, length=1000)
	public String getStrasse() {
		return this.strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}


	@Column(nullable=false, length=1000)
	public String getVorname() {
		return this.vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}


	//bi-directional many-to-one association to Bestellung
	@OneToMany(mappedBy="person1")
	public List<Bestellung> getBestellungs1() {
		return this.bestellungs1;
	}

	public void setBestellungs1(List<Bestellung> bestellungs1) {
		this.bestellungs1 = bestellungs1;
	}

	public Bestellung addBestellungs1(Bestellung bestellungs1) {
		getBestellungs1().add(bestellungs1);
		bestellungs1.setPerson1(this);

		return bestellungs1;
	}

	public Bestellung removeBestellungs1(Bestellung bestellungs1) {
		getBestellungs1().remove(bestellungs1);
		bestellungs1.setPerson1(null);

		return bestellungs1;
	}


	//bi-directional many-to-one association to Bestellung
	@OneToMany(mappedBy="person2")
	public List<Bestellung> getBestellungs2() {
		return this.bestellungs2;
	}

	public void setBestellungs2(List<Bestellung> bestellungs2) {
		this.bestellungs2 = bestellungs2;
	}

	public Bestellung addBestellungs2(Bestellung bestellungs2) {
		getBestellungs2().add(bestellungs2);
		bestellungs2.setPerson2(this);

		return bestellungs2;
	}

	public Bestellung removeBestellungs2(Bestellung bestellungs2) {
		getBestellungs2().remove(bestellungs2);
		bestellungs2.setPerson2(null);

		return bestellungs2;
	}


	//bi-directional many-to-one association to Korrespondenz
	@OneToMany(mappedBy="person1")
	public List<Korrespondenz> getKorrespondenzs1() {
		return this.korrespondenzs1;
	}

	public void setKorrespondenzs1(List<Korrespondenz> korrespondenzs1) {
		this.korrespondenzs1 = korrespondenzs1;
	}

	public Korrespondenz addKorrespondenzs1(Korrespondenz korrespondenzs1) {
		getKorrespondenzs1().add(korrespondenzs1);
		korrespondenzs1.setPerson1(this);

		return korrespondenzs1;
	}

	public Korrespondenz removeKorrespondenzs1(Korrespondenz korrespondenzs1) {
		getKorrespondenzs1().remove(korrespondenzs1);
		korrespondenzs1.setPerson1(null);

		return korrespondenzs1;
	}


	//bi-directional many-to-one association to Korrespondenz
	@OneToMany(mappedBy="person2")
	public List<Korrespondenz> getKorrespondenzs2() {
		return this.korrespondenzs2;
	}

	public void setKorrespondenzs2(List<Korrespondenz> korrespondenzs2) {
		this.korrespondenzs2 = korrespondenzs2;
	}

	public Korrespondenz addKorrespondenzs2(Korrespondenz korrespondenzs2) {
		getKorrespondenzs2().add(korrespondenzs2);
		korrespondenzs2.setPerson2(this);

		return korrespondenzs2;
	}

	public Korrespondenz removeKorrespondenzs2(Korrespondenz korrespondenzs2) {
		getKorrespondenzs2().remove(korrespondenzs2);
		korrespondenzs2.setPerson2(null);

		return korrespondenzs2;
	}


	//bi-directional many-to-one association to Rechnung
	@OneToMany(mappedBy="person")
	public List<Rechnung> getRechnungs() {
		return this.rechnungs;
	}

	public void setRechnungs(List<Rechnung> rechnungs) {
		this.rechnungs = rechnungs;
	}

	public Rechnung addRechnung(Rechnung rechnung) {
		getRechnungs().add(rechnung);
		rechnung.setPerson(this);

		return rechnung;
	}

	public Rechnung removeRechnung(Rechnung rechnung) {
		getRechnungs().remove(rechnung);
		rechnung.setPerson(null);

		return rechnung;
	}

}