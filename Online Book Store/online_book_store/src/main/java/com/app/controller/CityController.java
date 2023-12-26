package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.CityService;

@RestController
@RequestMapping("/cities")
@CrossOrigin(origins = "http://localhost:3000")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/{countryId}")
    public ResponseEntity<?> getCitiesByCountryId(@PathVariable Long countryId) {
        return new ResponseEntity<>(cityService.getCitiesByCountryId(countryId), HttpStatus.OK) ;
    }
}

