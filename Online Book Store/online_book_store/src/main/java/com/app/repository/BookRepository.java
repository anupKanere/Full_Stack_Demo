package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.dto.BookDTO;
import com.app.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	

	 @Query("SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.languages")
	    List<Book> findAllWithLanguages();
	 List<Book> findByTitleContainingIgnoreCase(String title);

}
