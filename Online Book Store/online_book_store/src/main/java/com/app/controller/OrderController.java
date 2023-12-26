package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.OrderDTO;
import com.app.dto.OrderRespDto;
import com.app.service.OrderService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;


	@GetMapping("/getOrdersForUser")
    public ResponseEntity<List<OrderRespDto>> getOrdersForUser(@RequestParam Long userId) {
        List<OrderRespDto> orders = orderService.getOrdersForUser(userId);
        return ResponseEntity.ok(orders);
    }

}
