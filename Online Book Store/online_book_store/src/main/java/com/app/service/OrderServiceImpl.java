package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.OrderDTO;
import com.app.dto.OrderRespDto;
import com.app.entities.Book;
import com.app.entities.Cart;
import com.app.entities.DeliveryStatus;
import com.app.entities.Order;
import com.app.entities.User;
import com.app.repository.BookRepository;
import com.app.repository.CartRepository;
import com.app.repository.OrderRepository;
import com.app.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CartRepository cartRepository;

	public Order getOrder(OrderDTO orderDTO) {
		Order order = new Order();

		// Retrieve cart by ID using Optional
		Optional<Cart> cartOptional = cartRepository.findById(orderDTO.getCartId());
		if (cartOptional.isPresent()) {
			Cart cart = cartOptional.get();

			order.setQuantity(cart.getTotalBooks());
			order.setCart(cart);
			List<Book> cartBooks = cart.getBooks();

		} else
			throw new RuntimeException("Cart not found");

		// Retrieve user by ID using Optional
		Optional<User> userOptional = userRepository.findById(orderDTO.getUserId());
		if (userOptional.isPresent()) {

			User user = userOptional.get();

		} else {
			throw new RuntimeException("User not found");
		}
		return order;

	}

	public OrderRespDto convertOrderToDto(Order order) {
		OrderRespDto orderRespDto = modelMapper.map(order, OrderRespDto.class);
		orderRespDto.setBooks(order.getBooks());

		return orderRespDto;
	}

	public void createOrderFromCart(Long userId) {
		Optional<Cart> optionalCart = cartRepository.findByUserId(userId);

		if (optionalCart != null) {
			Cart cart = optionalCart.get();
			Order order = new Order();
			order.setUser(cart.getUser());
			order.setBooks(new ArrayList<>(cart.getBooks())); // Copy books from cart
			order.setOrderDate(LocalDate.now());
			order.setCart(cart);
			order.setStatus(DeliveryStatus.ORDERED);
			order.setQuantity(1);


			orderRepository.save(order);

			cart.getBooks().clear();
			cartRepository.save(cart);
		}
	}

	public List<OrderRespDto> getOrdersForUser(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		List<Order> orders = user.get().getOrders();


		List<OrderRespDto> orderRespDtos = orders.stream().map(order -> new OrderRespDto(order.getOrderId(),
				order.getQuantity(), order.getOrderDate(), order.getStatus(),
				order.getBooks().stream()
						.map(book -> new Book(book.getBookId(), book.getTitle(), book.getBookImage(),
								book.getSellingPrice(), book.getGenre(), book.getEdition(), book.getAuthorName()))
						.collect(Collectors.toList())))
				.collect(Collectors.toList());

		return orderRespDtos;

	}

	public OrderRespDto mapToOrderDTO(Order order) {
		return modelMapper.map(order, OrderRespDto.class);
	}

}
