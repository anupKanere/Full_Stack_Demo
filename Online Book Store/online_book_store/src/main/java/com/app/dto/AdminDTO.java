
package com.app.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.app.entities.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
	private Long userId;
	@Length(min = 4, max = 20, message = "First name must be between 3 and 20 characters.")
	private String firstName;
	@Length(min = 4, max = 20, message = "Last name must be between 3 and 20 characters.")
	private String lastName;
	@Email(message = "Invalid email format")
	private String email;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}", 
			message = "Password must be at least 8 characters long and contain at least one uppercase letter,"
					+ " one lowercase letter, one digit, and one special character.")
	private String password;
	private String confirmPassword;
	private LocalDate dateOfBirth;
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	private String phoneNumber;
	private String address;
	private String city;

	private Role role;

}
