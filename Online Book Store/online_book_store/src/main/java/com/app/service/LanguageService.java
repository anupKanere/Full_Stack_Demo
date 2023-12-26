package com.app.service;

import java.util.List;

import com.app.dto.LanguageDTO;

public interface LanguageService {
	List<LanguageDTO> getAllLanguages(); 
	
     LanguageDTO addLanguage(LanguageDTO languageDTO);

}
