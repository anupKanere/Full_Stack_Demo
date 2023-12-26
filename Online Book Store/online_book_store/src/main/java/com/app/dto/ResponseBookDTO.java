package com.app.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBookDTO {
	@NotBlank
	@Length(max = 10, min = 3, message = "Book Title length should be between 5 to 10 characters.")
	private String title;
	private Long bookId;

	private List<String> languageNames = new ArrayList<>();

	private String bookImage;

	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate publicationDate;

	@NotBlank
	@Length(max = 15, min = 3, message = "Author Name should be between 3 to 15 characters.")
	private String authorName;

	@Min(value = 0, message = "Marked price should not be less than 0")
	private float markedPrice;

	@Min(value = 0, message = "Selling price should not be less than 0")
	private float sellingPrice;

	private int edition;

	@Min(value = 1, message = "Stock should be greater than 1")
	private int stock;

	private String genre;

	private List<String> reviews = new ArrayList<>();

}
