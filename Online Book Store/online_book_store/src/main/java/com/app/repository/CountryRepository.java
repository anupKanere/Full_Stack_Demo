package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Country;
@Repository 
public interface CountryRepository extends JpaRepository<Country, Long> {

}
