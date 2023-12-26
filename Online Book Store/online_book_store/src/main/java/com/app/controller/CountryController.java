package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CountryDTO;
import com.app.service.CountryService;

@RestController
@RequestMapping("/countries")
@CrossOrigin(origins = "http://localhost:3000")
public class CountryController {

	@Autowired
    private CountryService countryService;

	 @GetMapping
	    public ResponseEntity<?> getAllCountries() {
	        List<CountryDTO> countries = countryService.getAllCountries();

	        if (countries.isEmpty()) {
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.ok(countries);
	        }
	    }

	    @GetMapping("{countryId}")
	    public ResponseEntity<CountryDTO> getCountryById(@PathVariable Long countryId) {
	        CountryDTO country = countryService.getCountryById(countryId);

	        if (country != null) {
	            return ResponseEntity.ok(country);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
    
    
}
