package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dto.BookDTO;
import com.app.dto.ResponseBookDTO;
import com.app.entities.Book;
import com.app.entities.Language;
import com.app.entities.Review;
import com.app.repository.BookRepository;
import com.app.repository.LanguageRepository;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	  @Autowired
	    private LanguageRepository languageRepository;

	@Autowired
	private ModelMapper modelMapper;

	// to fetch all the books from the book table
	public List<ResponseBookDTO> getAllBooks() {
		List<Book> books = bookRepository.findAllWithLanguages();
//		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
//		System.out.println(books.stream().map(this::convertToDTO).collect(Collectors.toList()).get(0).getBookId());
//		System.out.println(books.stream().map(this::convertToDTO).collect(Collectors.toList()).get(1).getBookId());
//		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		return books.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private ResponseBookDTO convertToDTO(Book book) {
	    Hibernate.initialize(book.getLanguages());

	    ResponseBookDTO bookDTO = modelMapper.map(book, ResponseBookDTO.class);
	    
	    // Map genre information
	    if (book.getGenre() != null) {
	        bookDTO.setGenre(book.getGenre().toString()); // Map the genre enum value as a string
	    }
	   
	    List<String> languageNames = book.getLanguages().stream().map(Language::getName).collect(Collectors.toList());
	    bookDTO.setLanguageNames(languageNames);
	    return bookDTO;
	}

	// To add new Book object in the book table
	@Override
	public BookDTO addNewBook(BookDTO newBook) {
	    // Check if the languages provided are already present in the language table
	    List<String> languageNames = newBook.getLanguageNames();
	    List<Language> existingLanguages = languageRepository.findByNameIn(languageNames);

	    // Create a list to store new languages
	    List<Language> newLanguages = new ArrayList<>();

	    // Iterate through the language names provided in the book
	    for (String langName : languageNames) {
	        Optional<Language> existingLangOptional = existingLanguages.stream()
	                .filter(lang -> lang.getName().equalsIgnoreCase(langName))
	                .findFirst();

	        if (existingLangOptional.isPresent()) {
	            newLanguages.add(existingLangOptional.get());
	        } else {
	            // Language not found, create a new one and add it to the list of new languages
	            Language newLanguage = new Language(langName);
	            newLanguages.add(newLanguage);
	        }
	    }

	    // Set the new list of languages in the book DTO
	    newBook.setLanguageNames(newLanguages.stream().map(Language::getName).collect(Collectors.toList()));

	    // Create a Book entity and set its properties
	    Book book = modelMapper.map(newBook, Book.class);
	    book.setLanguages(newLanguages); // Set the languages to the book

	    // Save the book and new languages
	    Book savedBook = bookRepository.save(book);

	    // Map the saved book to a DTO and return
	    BookDTO existingBook = modelMapper.map(savedBook, BookDTO.class);
	    existingBook.setLanguageNames(newLanguages.stream().map(Language::getName).collect(Collectors.toList()));
	    return existingBook;
	}

	// to fetch all the books having provided title
	@Override
	public List<ResponseBookDTO> getBooksByTitle(String title) {
		List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
		return books.stream().map(book -> modelMapper.map(book, ResponseBookDTO.class)).collect(Collectors.toList());
	}

	// to delete the book by id
	@Override
	public String removeBookById(Long bookId) {
		// Check if the book with the given ID exists
		if (bookRepository.existsById(bookId)) {
			bookRepository.deleteById(bookId);
			return "Book with ID " + bookId + " has been removed.";
		} else {
			return "Book with ID " + bookId + " does not exist.";
		}
	}

	@Override
	public BookDTO updateBook(Long bookId, BookDTO updatedBookDTO) {
	    Optional<Book> existingBookOp = bookRepository.findById(bookId);
	    
	    if (existingBookOp.isPresent()) {
	        Book existingBook = existingBookOp.get();
	        
	        // Use ModelMapper to map properties from updatedBookDTO to existingBook
	        modelMapper.map(updatedBookDTO, existingBook);
	        
	        // Save the updated book
	        Book updatedBook = bookRepository.save(existingBook);
	        
	        // Map the updated book to a BookDTO and return
	        BookDTO updatedBookDto = modelMapper.map(updatedBook, BookDTO.class);
	        return updatedBookDto;
	    } else {
	        throw new ResourceNotFoundException("Book with ID " + bookId + " not found.");
	    }
	}


	@Override
	public ResponseBookDTO findBookById(Long bookId) {
	    Optional<Book> bookOptional = bookRepository.findById(bookId);
	    if (bookOptional.isPresent()) {
	        Book book = bookOptional.get();

	        // Extract review texts from Review entities
	        List<String> reviewTexts = book.getReviews().stream()
	            .map(Review::getReview)
	            .collect(Collectors.toList());

	        // Extract language names from Language entities
	        List<String> languageNames = book.getLanguages().stream()
	            .map(Language::getName)
	            .collect(Collectors.toList());

	        // Map the book entity to DTO and set the review texts and language names
	        ResponseBookDTO existingBookDTO = modelMapper.map(book, ResponseBookDTO.class);
	        existingBookDTO.setReviews(reviewTexts);
	        existingBookDTO.setLanguageNames(languageNames);

	        return existingBookDTO;
	    } else {
	        throw new ResourceNotFoundException(" Book with ID " + bookId + " not found.");
	    }
	}


	
	
	}