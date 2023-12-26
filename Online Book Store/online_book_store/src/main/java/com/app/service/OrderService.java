package com.app.service;

import java.util.List;

import com.app.dto.OrderDTO;
import com.app.dto.OrderRespDto;
import com.app.entities.Order;

public interface OrderService {

	public void createOrderFromCart(Long userId);
	public List<OrderRespDto> getOrdersForUser(Long userId);

}
