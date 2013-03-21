package ch.hslu.appe.fs1301.data.shared;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the KorrespondenzTemplate database table.
 * 
 */
@Entity
@Table(name="KorrespondenzTemplate")
public class KorrespondenzTemplate extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String inhalt;
	private int typ;

	public KorrespondenzTemplate() {
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

}