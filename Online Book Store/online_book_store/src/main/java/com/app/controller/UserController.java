package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.AdminDTO;
import com.app.dto.ApiResponse;
import com.app.dto.ListBookWithUserDTO;
import com.app.dto.ResponseBookDTO;
import com.app.dto.UserDTO;
import com.app.dto.UserSignInDTO;
import com.app.entities.Role;
import com.app.service.BookService;
import com.app.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;

	/**
	 * Add a new user to the system.
	 *
	 * @param userDto The UserDTO containing user data to be added.
	 * @return ResponseEntity containing the added UserDTO and HTTP status code.
	 */

	@PostMapping("/register-customer")
	public ResponseEntity<UserDTO> addNewUser(@RequestBody @Valid UserDTO userDto) {
		return new ResponseEntity<UserDTO>(userService.addNewUser(userDto), HttpStatus.CREATED);
	}

	/**
	 * Get a list of all users in the system.
	 *
	 * @return ResponseEntity containing the list of UserDTO objects and HTTP status
	 *         code.
	 */
	@GetMapping("/getAllUsers")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
		UserDTO userDTO = userService.getUserById(userId);
		return ResponseEntity.ok(userDTO);
	}

	@PatchMapping("/updateUser")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto) {
		UserDTO updatedUser = userService.updateUser(userDto);
		if (updatedUser != null) {
			return ResponseEntity.ok(updatedUser);
		} else {
			// Handle the case where user update fails
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully"), HttpStatus.OK);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signInUser(@RequestBody UserSignInDTO userSignInDTO) {
		UserDTO userDTO = userService.authenticateUser(userSignInDTO.getEmail(), userSignInDTO.getPassword());
		ListBookWithUserDTO listBookWithUserDTO;
		if (userDTO != null) {
			listBookWithUserDTO = new ListBookWithUserDTO();
			List<ResponseBookDTO> listBookDTO = bookService.getAllBooks();
			listBookWithUserDTO.setUserDTO(userDTO);
			listBookWithUserDTO.setListOfBookDTO(listBookDTO);
		} else {
			return new ResponseEntity<ApiResponse>(new ApiResponse("Invalid User Credentials "), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ListBookWithUserDTO>(listBookWithUserDTO, HttpStatus.CREATED);
	}

	@PostMapping("/admin-login")
	public ResponseEntity<?> signInAdmin(@RequestBody UserSignInDTO userSignInDTO) {

		UserDTO userDTO = userService.authenticateUser(userSignInDTO.getEmail(), userSignInDTO.getPassword());
		if (userDTO != null && userDTO.getRole() != Role.CUSTOMER ) {
			return new ResponseEntity<>(userService.adminLogin(userSignInDTO), HttpStatus.CREATED);
		}

		return new ResponseEntity<ApiResponse>(new ApiResponse("Invalid Admin Credentials "), HttpStatus.NOT_FOUND);
	}

	@PostMapping("/register-admin")
	public ResponseEntity<?> addAdmin(@RequestBody @Valid AdminDTO adminDto) {

		return new ResponseEntity<>(userService.addAdmin(adminDto), HttpStatus.CREATED);
	}

}