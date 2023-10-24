package com.apifinal.Grupo3.exceptions;

public class ItemPedidoNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ItemPedidoNotFoundException(Integer id) {
		super("Não foi encontrado ItemPedido com o id = "+id);
	}


}
