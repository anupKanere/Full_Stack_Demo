package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.CityDTO;
import com.app.entities.City;
import com.app.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<CityDTO> getCitiesByCountryId(Long countryId) {
        List<City> cities = cityRepository.findByCountry_CountryId(countryId);
        return cities.stream()
            .map(city -> mapCityToDTO(city))
            .collect(Collectors.toList());
    }

    private CityDTO mapCityToDTO(City city) {
        if (city != null) {
            CityDTO cityDTO = new CityDTO();
            cityDTO.setCityId(city.getCityId());
            cityDTO.setCityName(city.getCityName());
            cityDTO.setZipCode(city.getZipCode());
            return cityDTO;
        }
    
        return null;
    }
}