package com.apifinal.Grupo3.exceptions;

public class ClienteNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ClienteNotFoundException(Integer id) {
		super("Não foi encontrado Cliente com o id = "+id);
	}


}
