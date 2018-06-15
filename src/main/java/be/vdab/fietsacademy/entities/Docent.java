package be.vdab.fietsacademy.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import be.vdab.fietsacademy.enums.Geslacht;

@Entity
@Table(name = "docenten")
public class Docent implements Serializable {

	private static final long serialVersionUID = 1L;

	Docent() {

	}

	public Docent(String voornaam, String familienaam, BigDecimal wedde, String emailAdres, Geslacht geslacht) {
		this.voornaam = voornaam;
		this.familienaam = familienaam;
		this.wedde = wedde;
		this.emailAdres = emailAdres;
		this.geslacht = geslacht;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String voornaam;
	private String familienaam;
	private BigDecimal wedde;
	private String emailAdres;
	@Enumerated(EnumType.STRING)
	private Geslacht geslacht;

	public Geslacht getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(Geslacht geslacht) {
		this.geslacht = geslacht;
	}

	public long getId() {
		return id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public BigDecimal getWedde() {
		return wedde;
	}

	public String getEmailAdres() {
		return emailAdres;
	}

	public Object remove(Docent docent) {
		// TODO Auto-generated method stub
		return null;
	}

	public void opslag(BigDecimal percentage) {
		if (percentage.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException();
		}
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
		wedde = wedde.multiply(factor, new MathContext(2, RoundingMode.HALF_UP));
	}

}
