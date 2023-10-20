package com.apifinal.Grupo3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifinal.Grupo3.entities.ItemPedido;
import com.apifinal.Grupo3.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	ItemPedidoRepository itemPedidoRep;
	
	public List<ItemPedido> listarItemPedidos() {
		return itemPedidoRep.findAll();
	}
	
	public ItemPedido buscarItemPedidoId(Integer itemPedidoId) {
		return itemPedidoRep.findById(itemPedidoId).orElse(null);
	}

	public ItemPedido salvarItemPedido(ItemPedido itemPedido) {
		return itemPedidoRep.save(itemPedido);
	}

	public ItemPedido atualizarItemPedido(ItemPedido itemPedido) {
		return itemPedidoRep.save(itemPedido);
	}

	public Boolean deletarItemPedido(ItemPedido itemPedido) {
		if (itemPedido == null) {
			return false;	
		}

		ItemPedido itemPedidoExistente = buscarItemPedidoId(itemPedido.getItemPedidoId());
		if (itemPedidoExistente == null) {
			return false;
		}

		itemPedidoRep.delete(itemPedido);

		ItemPedido itemPedidoContinuaExistindo = buscarItemPedidoId(itemPedido.getItemPedidoId());
		if (itemPedidoContinuaExistindo == null) {
			return true;			
		}
		return false;
	}

}
