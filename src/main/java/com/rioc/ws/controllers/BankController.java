package com.rioc.ws.controllers;

import java.rmi.ServerException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.model.dto.AccountAdresseDto;
import com.rioc.ws.model.dto.AccountDto;
import com.rioc.ws.model.dto.BankAccountDto;
import com.rioc.ws.model.dto.BankDto;
import com.rioc.ws.services.account.IAccountService;
import com.rioc.ws.services.bank.IBankService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/bank")
public class BankController {
	
	public IBankService service;

	public BankController(IBankService service) {
		this.service = service;
	}
	
	@ApiOperation(value="Ajouter une banque à un utilisateur", notes="Chaque IBAN doit être unique")
	@PostMapping("/create")
	public ResponseEntity<BankDto> addBank(@Valid @RequestBody BankAccountDto newBank, BindingResult result) throws ServerException{
		BankDto bank = service.postBank(newBank);
		if(bank == null) {
			throw new ApiException("IBAN is not valid or already exists or ACCOUNT does not exist", HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<>(bank, HttpStatus.CREATED);
		}

	}
	
	@ApiOperation("Obtenir les banques selon l'ID d'un account")
	@GetMapping("/getById")
	public ResponseEntity<Object> getById(@Valid @RequestParam("id") int id) {
		List<BankDto> banks = service.getBankByAccountId(id);
		return new ResponseEntity<>(banks, HttpStatus.OK);
	}
	
	@ApiOperation("Obtenir tous les comptes banquaire")
	@GetMapping("/getAll")
	public ResponseEntity<List<BankDto>> getAll() {
		return new ResponseEntity<>(service.getBanks(), HttpStatus.OK);
	}
	
	@ApiOperation("Obtenir un compte banquaire à partir d'un IBAN")
	@GetMapping("/getByIban")
	public ResponseEntity<List<BankDto>> getByIban(@Valid @RequestParam("iban") String iban) {
		List<BankDto> banks = service.getBankByIban(iban);
		return new ResponseEntity<>(banks, HttpStatus.OK);
	}
	
	
	@ApiOperation("Supprimer une banque selon l'IBAN")
	@DeleteMapping("/deleteByIban")
	public ResponseEntity<Object> deleteByIban(@Valid @RequestParam("iban") String iban) {
		return new ResponseEntity<>(service.deleteByIban(iban), HttpStatus.OK);
	}

}
