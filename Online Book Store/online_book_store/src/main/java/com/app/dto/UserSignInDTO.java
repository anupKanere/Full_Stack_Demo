package com.app.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSignInDTO {

	@NotBlank(message = "Email can't be blank")
	@Email(message = "Invalid email format")
	private String email;
	@NotBlank
	@Length(min = 5, max = 20, message = "Invalid password length")
	private String password;

}
