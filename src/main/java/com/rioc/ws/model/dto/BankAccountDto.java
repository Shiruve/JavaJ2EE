package com.rioc.ws.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.rioc.ws.model.dao.Bank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class BankAccountDto {
	
	@NotEmpty(message="Le prénom ne peut pas être vide")
	@Size(min=2, max=50)
	private String firstName;
	
	@NotEmpty(message="Le nom ne peut pas être vide")
	@Size(min=2, max=50)
	private String lastName;

	private String iban;
	private String agence;
	private String bankName;
	
	public String getIban() {
		return iban;
	}
	
	public void setIban(String iban) {
		this.iban = iban;
	}
	
	public String getAgence() {
		return agence;
	}
	
	public void setAgence(String agence) {
		this.agence = agence;
	}
	
	public String getBankName() {
		return bankName;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

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


}
