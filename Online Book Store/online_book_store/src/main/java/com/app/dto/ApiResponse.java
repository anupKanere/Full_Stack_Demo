package com.app.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
	@JsonProperty
private String message;
private LocalDateTime timeStamp;

public ApiResponse(String message) {
	super();
	this.message = message;
	this.timeStamp = LocalDateTime.now();
}
}
