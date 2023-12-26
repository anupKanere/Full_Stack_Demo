package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Language;

public interface LanguageRepository extends JpaRepository<Language , Long> {

	List<Language> findByNameIn(List<String> languageNames);

}
