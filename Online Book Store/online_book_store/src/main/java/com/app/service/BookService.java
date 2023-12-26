package com.app.service;

import java.util.List;

import com.app.dto.BookDTO;
import com.app.dto.ResponseBookDTO;
import com.app.entities.Book;

public interface BookService {

	List<ResponseBookDTO> getAllBooks();
	BookDTO addNewBook(BookDTO newBook);
	List<ResponseBookDTO> getBooksByTitle(String title); 
     String removeBookById(Long bookId);
	ResponseBookDTO findBookById(Long bookId);
	BookDTO updateBook(Long bookId, BookDTO updatedBookDTO);

}
