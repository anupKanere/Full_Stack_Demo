package com.app.custom_exception;

public class UserNotFoundException extends RuntimeException{

	public UserNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
		System.out.println("User Not Found With This Id : "+message);
	}

		
}
