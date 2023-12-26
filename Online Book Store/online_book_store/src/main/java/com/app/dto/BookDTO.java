package com.app.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.app.entities.Cart;
import com.app.entities.Genre;
import com.app.entities.Language;
import com.app.entities.Review;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDTO {
	@NotBlank
	@Length(max = 20, min = 3, message = "Book Title lenght should be in between 3 to 20 characters.")
	private String title;

	private List<String> languageNames = new ArrayList<>();
	private String bookImage;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate publicationDate;
	@NotBlank
	@Length(max = 20, min = 3, message = "Author Name should be in between 3 to 20 characters.")
	private String authorName;
	@Min(value = 0, message = "Marked price should not be less than 0")
	private float markedPrice;
	@Min(value = 0, message = "Selling price should not be less than 0")
	private float sellingPrice;
	private int edition;
	@Min(value = 1, message = "Stock should be greater than 1")
	private int stock;
	private Genre genre;

}
