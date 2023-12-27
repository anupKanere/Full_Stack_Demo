package com.ak.fullstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ak.fullstack.exception.UserNotFoundException;
import com.ak.fullstack.model.User;
import com.ak.fullstack.repo.UserRepo;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	private UserRepo userRepo;

	@PostMapping("/addUser")
	public User addNewUser(@RequestBody User user) {
		return userRepo.save(user);
	}

	@GetMapping("getAll")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable Long id) throws UserNotFoundException {
		return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	@PutMapping("/edituser/{id}")
	public User updateUser(@RequestBody User newUser, @PathVariable Long id) throws UserNotFoundException {
		return userRepo.findById(id).map(user -> {
			user.setName(newUser.getName());
			user.setUserName(newUser.getUserName());
			user.setEmail(newUser.getEmail());
			return userRepo.save(user);
		}).orElseThrow(() -> new UserNotFoundException(id));
	}

	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable Long id) throws UserNotFoundException {
		if (!userRepo.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepo.deleteById(id);
		return "User with id = " + id + " has been DELETED Successfully";
	}

}
