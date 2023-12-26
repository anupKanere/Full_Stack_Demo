package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.BookDTO;
import com.app.dto.ResponseBookDTO;
import com.app.service.BookService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

	@Autowired
	private BookService bookService;

	// to fetch all the books from the book table 
	@GetMapping("/getAllBooks")
	public ResponseEntity<List<ResponseBookDTO>> getAllBooks() {
		return ResponseEntity.ok(bookService.getAllBooks());
	}
	
	// To add new Book object in the book table  
	@PostMapping("/addNewBook")
	public ResponseEntity<BookDTO> addNewBook(@RequestBody @Valid BookDTO newBook) {
		return new ResponseEntity<BookDTO>(bookService.addNewBook(newBook), HttpStatus.CREATED);
	}

	// to fetch all the books having given title   
	@GetMapping("/getBooksByTitle")
	public ResponseEntity<List<ResponseBookDTO>> getBooksByTitle(@RequestParam String title) {
		
		return new ResponseEntity<List<ResponseBookDTO>>(bookService.getBooksByTitle(title), HttpStatus.OK);
	}
	
	// to delete the book by id

	@DeleteMapping("/delete/{bookId}")
	public ResponseEntity<ApiResponse> removeBookById(@PathVariable Long bookId) {
	    String resultMessage = bookService.removeBookById(bookId);
	    ApiResponse response = new ApiResponse(resultMessage);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	
    }
	
		
	  @GetMapping("/{bookId}")
//	  @ExceptionHandler
	    public ResponseEntity<ResponseBookDTO> getBookById(@PathVariable Long bookId) {
	        ResponseBookDTO book = bookService.findBookById(bookId);
	        return ResponseEntity.ok(book);
	  }
	  
	    @PatchMapping("/update/{bookId}")
	    public ResponseEntity<BookDTO> updateBook(@PathVariable Long bookId, @RequestBody BookDTO updatedBook) {
	    	BookDTO updatedBookDto = bookService.updateBook(bookId, updatedBook);
	        return ResponseEntity.ok(updatedBookDto);
	    }
}
