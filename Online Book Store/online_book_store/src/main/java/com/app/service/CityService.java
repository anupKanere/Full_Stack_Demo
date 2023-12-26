package com.app.service;

import java.util.List;

import com.app.dto.CityDTO;

public interface CityService {
    List<CityDTO> getCitiesByCountryId(Long countryId);
}