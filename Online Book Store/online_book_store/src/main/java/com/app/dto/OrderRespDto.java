package com.app.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.app.entities.Book;
import com.app.entities.DeliveryStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRespDto {

	private Long id;
	private float price;
	private int quantity;
	private LocalDate orderDate;
	private List<Book> books = new ArrayList<Book>();
	private DeliveryStatus deliveryStatus = DeliveryStatus.ORDERED;

	public OrderRespDto(Long orderId, int quantity2, LocalDate orderDate2, DeliveryStatus status, List<Book> books) {
		this.id = orderId;
		this.quantity = quantity2;
		this.orderDate = orderDate2;
		this.books = books;
		this.deliveryStatus = status;

	}

}
