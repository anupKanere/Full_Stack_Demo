package com.app.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "books")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;
	
	private String bookImage;
	
	private String title;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "book_languages", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "language_id"))
	private List<Language> languages = new ArrayList<>();
	
	private LocalDate publicationDate;
	
	private String authorName;

	private int edition;

	@Column(length = 30) // Use a unique name for the column
	private boolean isAvailable = true;
	
	private int stock;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 30, name = "genre") // Use a unique name for the column
	private Genre genre;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Review> reviews = new ArrayList<Review>();
	
	@ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
	private List<Cart> carts = new ArrayList<>();

	private float markedPrice;
	
	private float sellingPrice;

	public Book(Long bookId, String title, String bookImage, float sellingPrice, Genre genre, int edition,
			String authorName) {
		this.bookId = bookId;
		this.bookImage = bookImage;
		this.title = title;
		this.edition = edition;
		this.genre = genre;
		this.authorName = authorName;
	}

}
