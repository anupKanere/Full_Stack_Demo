package com.app.service;

import java.util.List;

import com.app.dto.CountryDTO;

public interface CountryService {
    List<CountryDTO> getAllCountries();
    CountryDTO getCountryById(Long countryId);
}
