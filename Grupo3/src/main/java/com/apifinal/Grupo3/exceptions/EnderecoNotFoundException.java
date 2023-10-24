package com.apifinal.Grupo3.exceptions;

public class EnderecoNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public EnderecoNotFoundException(Integer id) {
		super("Não foi encontrado Endereco com o id = "+id);
	}


}
