package com.rioc.ws.services.adresse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rioc.ws.exceptions.ApiException;
import com.rioc.ws.mapper.IAdresseMapper;
import com.rioc.ws.model.dto.AdresseDto;
import com.rioc.ws.repositories.IAdresseRepository;

@Service
public class AdresseService implements IAdresseService{
	private IAdresseRepository repository;
	private IAdresseMapper adresseMapper;
	
	public AdresseService(IAdresseRepository repository, IAdresseMapper adresseMapper) {
		super();
		this.repository = repository;
		this.adresseMapper = adresseMapper;
	}

	public AdresseDto addAdresse(String q) {
		String query = "https://api-adresse.data.gouv.fr/search/?q="+q+"&limit=1";
		query = query.replace(" ", "+");
		query = query.replace("'", "%27");
		URL url;
		try {
			url = new URL(query);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode json = mapper.readTree(url);
			JsonNode features = json.get("features");
			
			for(JsonNode feature : features) {
				JsonNode properties = feature.get("properties");
				AdresseDto adresse = new AdresseDto();
				adresse.setLabel(properties.get("label").asText());
				adresse.setName(properties.get("name").asText());
				adresse.setCity(properties.get("city").asText());
				adresse.setHousenumber(properties.get("housenumber").asText());
				adresse.setStreet(properties.get("street").asText());
				adresse.setPostcode(properties.get("postcode").asText());
				return adresseMapper.adresseToAdresseDto(repository.save(adresseMapper.adresseDtoToAdresse(adresse)));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public AdresseDto getAdresse(String q) {
		String query = "https://api-adresse.data.gouv.fr/search/?q="+q+"&limit=1";
		query = query.replace(" ", "+");
		query = query.replace("'", "%27");
		URL url;
		try {
			url = new URL(query);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode json = mapper.readTree(url);
			JsonNode features = json.get("features");
			
			for(JsonNode feature : features) {
				JsonNode properties = feature.get("properties");
				AdresseDto adresse = new AdresseDto();
				String label = properties.get("label").asText().replace("’", "'");
				if(label.equalsIgnoreCase(q)) {
					adresse.setLabel(properties.get("label").asText());
					adresse.setName(properties.get("name").asText());
					adresse.setCity(properties.get("city").asText());
					adresse.setHousenumber(properties.get("housenumber").asText());
					adresse.setStreet(properties.get("street").asText());
					adresse.setPostcode(properties.get("postcode").asText());
					return adresse;
				}
				else throw new ApiException("Address not found", HttpStatus.NOT_FOUND);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
