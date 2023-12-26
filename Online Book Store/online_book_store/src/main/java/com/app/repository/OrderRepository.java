package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.Order;
//@Repository
//public interface OrderRepository extends JpaRepository<Order, Long>  {

//    @Query("select new com.app.entity.projection.OrderBook(b.title as name, b.price as price, o.orderDate as purchaseTime) " +
//            "from Order o join o.books b where o.user.email = ?1")
//     List<OrderBook> findAllPurchaseBooksByUserEmail(String email);

	@Repository
	public interface OrderRepository extends JpaRepository<Order, Long> {
	    List<Order> findAllByUserUserId(Long userId);
	}

	

