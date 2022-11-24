package com.rioc.ws.controllers;

import java.rmi.ServerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.model.dto.AdresseDto;
import com.rioc.ws.services.adresse.IAdresseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping("/adresse")
public class AdresseController {

	
	public IAdresseService service;

	public AdresseController(IAdresseService service) {
		this.service = service;
	}
	
	@ApiOperation(value="Ajouter une adresse", notes="Test pour ajouter une adresse")
	@PostMapping
	public ResponseEntity<AdresseDto> create(@RequestBody String query) throws ServerException{
		AdresseDto adresse = service.addAdresse(query);
		if(adresse == null) {
			throw new ApiException("Aucune réponse correspondante", HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<>(adresse, HttpStatus.CREATED);
		}
	}
}
