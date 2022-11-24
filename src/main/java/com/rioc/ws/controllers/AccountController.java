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
import com.rioc.ws.services.account.IAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/account")
public class AccountController {
	
	public IAccountService service;

	public AccountController(IAccountService service) {
		this.service = service;
	}
	
	@ApiOperation(value="Créer un compte", notes="Chaque compte est unique (nom et prénom différents). L'adresse saisie doit être exacte (ville et code postal inclus).")
	@PostMapping("/create")
	public ResponseEntity<AccountDto> create(@Valid @RequestBody AccountAdresseDto newAccount, BindingResult result) throws ServerException{
		AccountDto account = service.postAccount(newAccount);
		if(account == null) {
			throw new ApiException("Account already exists", HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<>(account, HttpStatus.CREATED);
		}
	}
	
	@ApiOperation("Supprimer tous les comptes")
	@DeleteMapping("/deleteAll")
	public void deleteAll() {
		service.deleteAll();
	}
	
	@ApiOperation("Obtenir tous les comptes")
	@GetMapping("/getAll")
	public ResponseEntity<List<AccountDto>> getAll() {
		return new ResponseEntity<>(service.getAccounts(), HttpStatus.OK);
	}
	
	@ApiOperation("Obtenir un compte par le nom et le prénom")
	@GetMapping("/getByName")
	public ResponseEntity<List<AccountDto>> getByName(@Valid @RequestParam("firstName") String firstName, @Valid @RequestParam("lastName") @NotEmpty String lastName) {
		return new ResponseEntity<>(service.getAccountsByName(firstName, lastName), HttpStatus.OK);
	}
	
	@ApiOperation("Supprimer un compte par le nom et le prénom")
	@DeleteMapping("/deleteByName")
	public ResponseEntity<Object> deleteByName(@Valid @RequestParam("firstName") String firstName, @Valid @RequestParam("lastName") @NotEmpty String lastName) {
		return new ResponseEntity<>(service.deleteAccountByName(firstName, lastName), HttpStatus.OK);
	}
	
	@ApiOperation("Supprimer un compte par l'ID")
	@DeleteMapping("/deleteById")
	public ResponseEntity<Object> deleteById(@Valid @RequestParam("id") int id) {
		return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
	}
	
	@ApiOperation("Obtenir un compte par l'ID")
	@GetMapping("/getById")
	public ResponseEntity<Object> getById(@Valid @RequestParam("id") int id) {
		return new ResponseEntity<>(service.getAccountById(id), HttpStatus.OK);
	}
}
