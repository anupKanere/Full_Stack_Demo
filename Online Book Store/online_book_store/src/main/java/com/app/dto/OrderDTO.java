package com.app.dto;

import java.time.LocalDate;

import com.app.entities.DeliveryStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class OrderDTO {

	@NotNull
	private Long userId;
	@Min(value = 0, message = "Price should not be less than 0")
	private float price;
	@NotNull
	private Long cartId;
	@JsonIgnore
	private LocalDate orderDate;
	private DeliveryStatus deliveryStatus;

}
