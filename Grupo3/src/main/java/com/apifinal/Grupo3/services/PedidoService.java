package com.apifinal.Grupo3.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifinal.Grupo3.DTO.ItemPedidoDTO;
import com.apifinal.Grupo3.DTO.PedidoDTO;
import com.apifinal.Grupo3.entities.ItemPedido;
import com.apifinal.Grupo3.entities.Pedido;
import com.apifinal.Grupo3.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepo;

	public List<Pedido> listarPedidos() {
		return pedidoRepo.findAll();
	}

	
	public List<PedidoDTO> listarPedidosComItens() {
	    List<Pedido> pedidos = pedidoRepo.findAll();
	    List<PedidoDTO> pedidosDTO = new ArrayList<>();

	    for (Pedido pedido : pedidos) {
	        PedidoDTO pedidoDTO = new PedidoDTO();
	        pedidoDTO.setPedidoId(pedido.getPedidoId());
	        pedidoDTO.setDataPedido(pedido.getDataPedido());
	        pedidoDTO.setValorTotal(pedido.getValorTotal());

	   
	        List<ItemPedidoDTO> itensDTO = new ArrayList<>();
	        for (ItemPedido itemPedido : pedido.getItensPedidos()) {
	            ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
	            itemPedidoDTO.setItemPedidoId(itemPedido.getProduto().getProdutoId());
	            itemPedidoDTO.setNome(itemPedido.getProduto().getNome());
	            itemPedidoDTO.setPrecoVenda(itemPedido.getProduto().getValorUnitario());
	            itemPedidoDTO.setQuantidade(itemPedido.getQuantidade());
	            itemPedidoDTO.setValorBruto(itemPedido.getValorBruto());
	            itemPedidoDTO.setPercentualDesconto(itemPedido.getPercentualDesconto());
	            itemPedidoDTO.setValorLiquido(itemPedido.getValorLiquido());
	            itensDTO.add(itemPedidoDTO);
	        }

	        pedidoDTO.setItensPedido(itensDTO);
	        pedidosDTO.add(pedidoDTO);
	    }

	    return pedidosDTO;
	}

	public Pedido buscarPedidoPorId(Integer id) {
		return pedidoRepo.findById(id).orElse(null);
	}

	public PedidoDTO PedidoRelatorioPorId(Integer id) {
		Pedido pedido = pedidoRepo.findById(id).orElse(null);

		if (pedido != null) {
			PedidoDTO pedidoDTO = new PedidoDTO(pedido.getPedidoId(),pedido.getDataPedido(), pedido.getValorTotal());
			return pedidoDTO;

		}

		return null;
	}

	public Pedido salvarPedido(Pedido pedido) {
		Date dataAtual = new Date();
		
		if(pedido.getDataPedido() != null && pedido.getDataPedido().before(dataAtual)) {
			throw new IllegalArgumentException("A data do pedido não pode ser retroativa.");
		}
		
		return pedidoRepo.save(pedido);
	}

	public Pedido atualizarPedido(Pedido pedido) {
		return pedidoRepo.save(pedido);
	}

	public Boolean deletarPedido(Pedido pedido) {
		if (pedido == null) {
			return false;
		}
		Pedido pedidoExistente = buscarPedidoPorId(pedido.getPedidoId());

		if (pedidoExistente == null) {
			return false;
		}
		pedidoRepo.delete(pedido);

		Pedido pedidoContinuaExistindo = buscarPedidoPorId(pedido.getPedidoId());

		if (pedidoContinuaExistindo == null) {
			return true;
		}
		return false;

	}
}
