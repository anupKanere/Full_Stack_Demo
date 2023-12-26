package com.app.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.app.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private Long userId;
	@NotBlank(message = "First name can't be blank")
	@Length(min = 3, max = 20, message = "First name must be between 3 and 20 characters.")
	private String firstName;
	@NotBlank(message = "Last  name can't be blank")
	@Length(min = 3, max = 20, message = "Last name must be between 3 and 20 characters.")
	private String lastName;
	@Email(message = "Invalid email format")
	private String email;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}", message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
	private String password;
	private String confirmPassword;
	private LocalDate dateOfBirth;
	private String address;
	private String city;
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	private String phoneNumber;
	private LocalDate registeredDate;
	private Role role;

	public UserDTO(
//			@NotBlank(message = "First name can't be blank") @Length(min = 4, max = 20, message = "Invalid first name!!!!!!") String firstName,
//			@NotBlank(message = "Last  name can't be blank") String lastName,
			@Email(message = "Invalid email format") String email, String password, String confirmPassword,
			LocalDate dateOfBirth, String address, String city,
			@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits") String phoneNumber,
			LocalDate registeredDate, Role role) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.dateOfBirth = dateOfBirth;

		this.address = address;

		this.city = city;
		this.phoneNumber = phoneNumber;
		this.registeredDate = registeredDate;
		this.role = role;

	}

}
