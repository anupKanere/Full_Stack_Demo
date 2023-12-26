package com.app.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.UserNotFoundException;
import com.app.dto.AdminDTO;
import com.app.dto.UserDTO;
import com.app.dto.UserSignInDTO;
import com.app.entities.Cart;
import com.app.entities.City;
import com.app.entities.Role;
import com.app.entities.User;
import com.app.repository.BookRepository;
import com.app.repository.CartRepository;
import com.app.repository.CityRepository;
import com.app.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;

@Transactional
@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookService bookService;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CityRepository cityRepository;

	private Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * Add a new user to the database.
	 *
	 * @param userDto The UserDTO containing user data to be added.
	 * @return The added UserDTO after saving to the database.
	 */

	@Override
	public UserDTO addNewUser(UserDTO userDto) {
		User user = modelMapper.map(userDto, User.class);
		Cart cart = new Cart();
		cart.setCartId(user.getUserId());
		cart.setUser(user);
		user.setCart(cart);
		City city = cityRepository.findByCityName(userDto.getCity());
		user.setCity(city);

		String password = user.getPassword();
		String hashedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			hashedPassword = DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		user.setPassword(hashedPassword);

		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser, UserDTO.class);
	}

	/**
	 * Retrieve a list of all users from the database.
	 *
	 * @return List of UserDTO objects representing all users.
	 */

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(this::mapToUserDTO).collect(Collectors.toList());
	}

	/**
	 * Maps a User entity to a UserDTO.
	 *
	 * @param user The User entity to be mapped.
	 * @return The corresponding UserDTO.
	 */

	private UserDTO mapToUserDTO(User user) {
		UserDTO userDto = modelMapper.map(user, UserDTO.class);
		return userDto;
	}

	@Override
	public UserDTO updateUser(UserDTO userDto) {
		if (userDto.getUserId() == null) {
			return null;
		}
		User existingUser = userRepository.findById(userDto.getUserId()).orElse(null);
		if (existingUser != null) {
			if (userDto.getFirstName() != null) {
				existingUser.setFirstName(userDto.getFirstName());
			}
			if (userDto.getLastName() != null) {
				existingUser.setLastName(userDto.getLastName());
			}

			if (userDto.getAddress() != null) {
				existingUser.setAddress(userDto.getAddress());
			}
			if (userDto.getCity() != null) {
				existingUser.setCity(cityRepository.findByCityName(userDto.getCity()));
			}
			if (userDto.getEmail() != null) {
				existingUser.setEmail(userDto.getEmail());
			}
			if (userDto.getPhoneNumber() != null) {
				existingUser.setPhoneNumber(userDto.getPhoneNumber());
			}
			if (userDto.getPassword() != null) {
				existingUser.setPassword(userDto.getPassword());
			}
			if (userDto.getDateOfBirth() != null) {
				existingUser.setDateOfBirth(userDto.getDateOfBirth());
			}
			User updatedUser = userRepository.save(existingUser);
			return modelMapper.map(updatedUser, UserDTO.class);
		}
		return null;
	}

	@Override
	public void deleteUser(Long userId) {
		Optional<User> existingUser = userRepository.findById(userId);
		if (existingUser != null) {
			User user = existingUser.get();
			cartRepository.deleteById(user.getCart().getCartId());
			userRepository.deleteById(userId);
		}
	}

	@Override
	public UserDTO authenticateUser(String email, String password) {
		log.info(password);
		User user = userRepository.findByEmail(email);
		log.info("username" + user.getPassword());
		if (user != null && comparePasswords(password, user.getPassword())) {
			return modelMapper.map(user, UserDTO.class);
		}

		return null;
	}

	private boolean comparePasswords(String enteredPassword, String storedPasswordHash) {
		String enteredPasswordHash = hashPasswordMD5(enteredPassword);
		return enteredPasswordHash.equalsIgnoreCase(storedPasswordHash);
	}

	private String hashPasswordMD5(String password) {
		try {
			log.info(" inside MD5 " + password);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString = new StringBuilder();

			for (byte hashByte : hashBytes) {
				String hex = Integer.toHexString(0xff & hashByte);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			log.info("After Hashing" + hexString.toString());
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 algorithm not available", e);
		}
	}

	@Override
	public UserDTO getUserById(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

		return modelMapper.map(user, UserDTO.class);
	}

	@Override
	public AdminDTO adminLogin(UserSignInDTO userSignInDTO) {
		User user = userRepository.findByEmail(userSignInDTO.getEmail());

		if (user != null && comparePasswordsAdmin(userSignInDTO.getPassword(), user.getPassword())
				&& user.getRole().equals(Role.ADMIN)) {
			return modelMapper.map(user, AdminDTO.class);
		}

		throw new UserNotFoundException("Invalid email or password..!");
	}

	private boolean comparePasswordsAdmin(String enteredPassword, String storedPasswordHash) {
		String enteredPasswordHash = hashPasswordMD6(enteredPassword);
		return enteredPasswordHash.equalsIgnoreCase(storedPasswordHash);
	}

	private String hashPasswordMD6(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
			StringBuilder hexString = new StringBuilder();

			for (byte hashByte : hashBytes) {
				String hex = Integer.toHexString(0xff & hashByte);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("MD5 algorithm not available", e);
		}
	}

	@Override
	public AdminDTO addAdmin(AdminDTO adminDto) {
		User user = modelMapper.map(adminDto, User.class);
		Cart cart = new Cart();
		cart.setCartId(user.getUserId());
		cart.setUser(user);
		user.setCart(cart);

		String password = user.getPassword();
		String hashedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			hashedPassword = DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		user.setPassword(hashedPassword);

		City city = cityRepository.findByCityName(adminDto.getCity());
		user.setCity(city);

		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser, AdminDTO.class);
	}

}
