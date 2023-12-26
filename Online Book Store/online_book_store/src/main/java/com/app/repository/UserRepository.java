package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 *	The ?1 is a parameter placeholder. 
	 *	It represents the first parameter passed to the query method.
	 */
//	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findUserByEmailAndPassword(String email,String password);

	User findByEmail(String email);

	
}
