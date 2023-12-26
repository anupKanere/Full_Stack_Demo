package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {
	
	City findByCityName(String cityName);
	List<City> findByCountryName(String countryName);
	 List<City> findByCountry_CountryId(Long countryId);

}
