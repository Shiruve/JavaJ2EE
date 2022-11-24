package com.rioc.ws.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AccountAdresseDto {
	
	@NotEmpty(message="Le prénom ne peut pas être vide")
	@Size(min=2, max=50)
	private String firstName;
	
	@NotEmpty(message="Le nom ne peut pas être vide")
	@Size(min=2, max=50)
	private String lastName;
	
	@NotEmpty(message="Saisissez une adresse")
	@Size(min=2, max=50)
	private String adresse;
	
	@NotEmpty(message="Saisissez une ville")
	@Size(min=2, max=50)
	private String city;
	
	@NotEmpty(message="Saisissez un code postal")
	@Size(min=5, max=5)
	private String postalCode;
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


}
