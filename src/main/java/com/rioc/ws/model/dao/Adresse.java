package com.rioc.ws.model.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "ADRESSE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Adresse {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADRESSE_ID", unique = true, nullable = false)
	private int adresseId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LABEL")
	private String label;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "HOUSE_NUMBER")
	private String housenumber;
	
	@Column(name = "STREET")
	private String street;
	
	@Column(name = "POST_CODE")
	private String postcode;
	
	@OneToOne(mappedBy="adresse")
	private Account account;

	public Adresse() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getAdresseId() {
		return adresseId;
	}

	public void setAdresseId(int adresseId) {
		this.adresseId = adresseId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	
}
