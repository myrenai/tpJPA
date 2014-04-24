package fr.istic.tpjpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4226691999230562844L;
	@Column(name="CITY")
	private String city;
	@Column(name="STREET")
	private String street;
	@Column(name="CODE_POSTAL")
	private String codePostal;
	@Column(name="NATION")
	private String nation;
	
	public Address (){}
	
	public static Address build(){
		return new Address();
	}
	
	public String getNation() {
		return nation;
	}
	public Address setNation(String nation) {
		this.nation = nation;
		return this;
	}
	public String getCity() {
		return city;
	}
	public Address setCity(String city) {
		this.city = city;
		return this;
	}
	public String getStreet() {
		return street;
	}
	public Address setStreet(String street) {
		this.street = street;
		return this;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public Address setCodePostal(String codePostal) {
		this.codePostal = codePostal;
		return this;
	}
	@Override
	public String toString() {
		return "Address [city=" + city + ", street=" + street + ", codePostal="
				+ codePostal + ", nation=" + nation + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
				+ ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result + ((nation == null) ? 0 : nation.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (codePostal == null) {
			if (other.codePostal != null)
				return false;
		} else if (!codePostal.equals(other.codePostal))
			return false;
		if (nation == null) {
			if (other.nation != null)
				return false;
		} else if (!nation.equals(other.nation))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}
	
	
	
	
}
