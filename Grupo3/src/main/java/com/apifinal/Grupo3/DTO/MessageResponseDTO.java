package com.apifinal.Grupo3.DTO;

public class MessageResponseDTO {
	private String message;

	public MessageResponseDTO(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}