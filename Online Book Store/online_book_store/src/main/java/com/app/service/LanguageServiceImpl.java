package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.LanguageDTO;
import com.app.entities.Language;
import com.app.repository.LanguageRepository;

@Service
public class LanguageServiceImpl implements LanguageService{

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ModelMapper modelMapper;

    public LanguageDTO addLanguage(LanguageDTO languageDTO) {
        Language language = modelMapper.map(languageDTO, Language.class);
        Language addedLanguage = languageRepository.save(language);
        return modelMapper.map(addedLanguage, LanguageDTO.class);
    }

    public List<LanguageDTO> getAllLanguages() {
        List<Language> languages = languageRepository.findAll();
        return languages.stream()
                .map(language -> modelMapper.map(language, LanguageDTO.class))
                .collect(Collectors.toList());
    }
}
