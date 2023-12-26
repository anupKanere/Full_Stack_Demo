package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.BooksFromCartOrderDTO;
import com.app.service.CartService;
import com.app.service.OrderService;

@RestController
@RequestMapping("/carts")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    
    @PostMapping("/buyNow")
    public ResponseEntity<String> buyNow(@RequestParam Long userId) {
        orderService.createOrderFromCart(userId);
        return ResponseEntity.ok("Order placed successfully.");
    }
    
    @GetMapping("/getBooksInCart")
    public ResponseEntity<List<BooksFromCartOrderDTO>> getBooksInCart(@RequestParam Long userId) {
        List<BooksFromCartOrderDTO> booksInCart = cartService.getBooksInCart(userId);
        return ResponseEntity.ok(booksInCart);
    }
    
    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestParam Long userId, @RequestParam Long bookId) {
        cartService.addToCart(userId, bookId);
        return ResponseEntity.ok("Book added to cart successfully.");
    }
    
   


}
