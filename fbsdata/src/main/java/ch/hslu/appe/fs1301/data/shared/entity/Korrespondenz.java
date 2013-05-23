package ch.hslu.appe.fs1301.data.shared.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Korrespondenz database table.
 * 
 */
@Entity
@Table(name="Korrespondenz")
public class Korrespondenz extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String inhalt;
	private int typ;
	private Person person1;
	private Person person2;

	public Korrespondenz() {
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
	public String getInhalt() {
		return this.inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}


	@Column(nullable=false)
	public int getTyp() {
		return this.typ;
	}

	public void setTyp(int typ) {
		this.typ = typ;
	}


	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Kunde_ID", nullable=false)
	public Person getPerson1() {
		return this.person1;
	}

	public void setPerson1(Person person1) {
		this.person1 = person1;
	}


	//bi-directional many-to-one association to Person
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="User_ID", nullable=false)
	public Person getPerson2() {
		return this.person2;
	}

	public void setPerson2(Person person2) {
		this.person2 = person2;
	}

}