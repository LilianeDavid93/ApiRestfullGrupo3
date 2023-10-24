package com.apifinal.Grupo3.exceptions;

public class PedidoNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public PedidoNotFoundException(Integer id) {
		super("Não foi encontrado Pedido com o id = "+id);
	}


}
