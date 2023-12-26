package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dto.BooksFromCartOrderDTO;
import com.app.entities.Book;
import com.app.entities.Cart;
import com.app.entities.User;
import com.app.repository.BookRepository;
import com.app.repository.CartRepository;
import com.app.repository.UserRepository;

@Transactional
@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ModelMapper modelMapper;

    
	@Override
	public Cart createCartForUser(User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		return cartRepository.save(cart);
	}

	public void addToCart(Long userId, Long bookId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		Optional<Book> optionalBook = bookRepository.findById(bookId);

		if (optionalUser.isPresent() && optionalBook.isPresent()) {
			User user = optionalUser.get();
			Book book = optionalBook.get();

			Cart cart = user.getCart();

			if (cart == null) {
				cart = new Cart(); // Create a new cart if user's cart is null
				user.setCart(cart);
			}

			cart.getBooks().add(book);
			cartRepository.save(cart);
		}
	}

	public List<BooksFromCartOrderDTO> getBooksInCart(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
//        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
			if (optionalUser.get().getCart() != null) {
//        	 return cart.getBooks();
				Cart cart = optionalUser.get().getCart();
				return cart.getBooks().stream().map(this::mapToCartBooksDTO).collect(Collectors.toList());
			}
		}
		return null;
	}

	private BooksFromCartOrderDTO mapToCartBooksDTO(Book book) {
		BooksFromCartOrderDTO booksFromCartOrOrderDTO = modelMapper.map(book, BooksFromCartOrderDTO.class);
		return booksFromCartOrOrderDTO;
	}

	
}