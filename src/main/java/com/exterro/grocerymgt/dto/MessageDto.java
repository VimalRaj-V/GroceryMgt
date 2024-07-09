package com.exterro.grocerymgt.dto;

/*
Author name : vimalraj.vijayakumar
Creation Date : 26-Jun-2024
*/

public class MessageDto {
	private String message;

	public MessageDto() {
		super();
	}

	public MessageDto(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageDto [message=" + message + "]";
	}
	
}
