package com.apifinal.Grupo3.exceptions;

public class NoSuchElementException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NoSuchElementException(String message) {
		super(message);
	}
	
	public NoSuchElementException(String entidade, Integer id) {
		super("Não foi encontrado(a) " + entidade + " com o id = "+id);
	}
}
