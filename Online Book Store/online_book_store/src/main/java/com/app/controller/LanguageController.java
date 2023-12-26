package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.LanguageDTO;
import com.app.service.LanguageServiceImpl;

@RestController
@RequestMapping("/languages")
@CrossOrigin(origins = "http://localhost:3000")
public class LanguageController {

    @Autowired
    private LanguageServiceImpl languageService;
    
    @PostMapping
    public ResponseEntity<LanguageDTO> addLanguage(@RequestBody LanguageDTO languageDTO) {
        LanguageDTO addedLanguage = languageService.addLanguage(languageDTO);
        return new ResponseEntity<>(addedLanguage, HttpStatus.CREATED);
    }

    @GetMapping
    public List<LanguageDTO> getAllLanguages() {
        return languageService.getAllLanguages();
    }

}