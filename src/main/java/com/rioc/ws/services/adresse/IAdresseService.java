package com.rioc.ws.services.adresse;

import com.rioc.ws.model.dto.AdresseDto;

public interface IAdresseService {
	public AdresseDto addAdresse(String q);
	public AdresseDto getAdresse(String q);
}
