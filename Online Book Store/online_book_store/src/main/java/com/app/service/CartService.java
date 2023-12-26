package com.app.service;

import java.util.List;

import com.app.dto.BooksFromCartOrderDTO;
import com.app.entities.Cart;
import com.app.entities.User;

public interface CartService {

	public Cart createCartForUser(User user);


	List<BooksFromCartOrderDTO> getBooksInCart(Long userId);

	public void addToCart(Long userId, Long bookId);


}
