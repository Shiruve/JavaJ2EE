package com.rioc.ws.mapper;

import org.mapstruct.Mapper;

import com.rioc.ws.model.dao.Adresse;
import com.rioc.ws.model.dto.AdresseDto;

@Mapper(componentModel = "spring")
public interface IAdresseMapper {
	AdresseDto adresseToAdresseDto(Adresse adresse);
	Adresse adresseDtoToAdresse(AdresseDto adresseDto);
}
