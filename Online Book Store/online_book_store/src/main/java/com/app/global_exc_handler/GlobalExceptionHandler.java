package com.app.global_exc_handler;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.app.custom_exception.ResourceNotFoundException;
import com.app.custom_exception.UserAlreadyExistsException;
import com.app.dto.ApiResponse;
import com.app.dto.ErrorResponse;

@RestControllerAdvice
//@RestControllerAdvice = @ControllerAdvice + @ResponseEntity

public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		System.out.println("in global handler : method arg invalid");
		Map<String, String> errorMap = ex.getFieldErrors().stream() // Stream<FieldError>
				.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		ErrorResponse resp = new ErrorResponse(errorMap.toString());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
		System.out.println("in global handler : method arg type mismatch " + ex);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(
				"Type mismatch for param : " + ex.getName() + " Details : " + ex.getMostSpecificCause().getMessage()));
	}

	// add new exc handler method to catch specifically : custom exc :
	// ResourceNotFound
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e) {
		System.out.println("in handle res not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getMessage()));

	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<?> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
		System.out.println("err " + e);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception e) {
		System.out.println("in run time exc handler");
		e.printStackTrace();
		ErrorResponse err = new ErrorResponse("Something Went Wrong : Please try again");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}
}
