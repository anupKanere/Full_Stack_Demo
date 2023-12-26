package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BooksFromCartOrderDTO {
	private Long bookId;
	private String bookImage;
	private String title;
	private String authorName;
	private float sellingPrice;

	

}
