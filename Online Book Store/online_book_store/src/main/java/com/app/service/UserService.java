package com.app.service;

import java.util.List;

import com.app.dto.AdminDTO;
import com.app.dto.ResponseBookDTO;
import com.app.dto.UserDTO;
import com.app.dto.UserSignInDTO;

import jakarta.validation.Valid;

public interface UserService {
	public UserDTO addNewUser(UserDTO userDto);

	public List<UserDTO> getAllUsers();
	
	public UserDTO updateUser( UserDTO userDto);

	public void deleteUser(Long userId);
	
	public UserDTO authenticateUser(String email, String password);

	public UserDTO getUserById(Long userId);

	public AdminDTO adminLogin(UserSignInDTO userSignInDTO);

	public AdminDTO addAdmin(AdminDTO adminDto);
	

}
