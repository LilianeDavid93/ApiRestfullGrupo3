package com.apifinal.Grupo3.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifinal.Grupo3.DTO.PedidoDTO;
import com.apifinal.Grupo3.entities.Pedido;
import com.apifinal.Grupo3.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepo;

	public List<Pedido> listarPedidos() {
		return pedidoRepo.findAll();
	}

	public List<PedidoDTO> Pedidos() {
		List<Pedido> pedidos = pedidoRepo.findAll();
		List<PedidoDTO> pedidosDTO = new ArrayList<>();

		for (Pedido pedido : pedidos) {
			PedidoDTO pedidoResDTO = new PedidoDTO();
			pedidoResDTO.setPedidoId(pedido.getPedidoId());
			pedidoResDTO.setDataPedido(pedido.getDataPedido());
			pedidoResDTO.setValorTotal(pedido.getValorTotal());
			pedidoResDTO.setItens(pedido.getItensPedidos());
			pedidosDTO.add(pedidoResDTO);
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
		return pedidoRepo.save(pedido);
	}

	public Pedido atualizarPedido(Pedido pedido) {
		return pedidoRepo.save(pedido);
	}

	public Boolean deletarPedido(Pedido pedido) {
		if (pedido == null) {
			return false;
		}
		Pedido pedidoExistente = buscarPedidoPorId(pedido.getNumeroMatriculaPedido());

		if (pedidoExistente == null) {
			return false;
		}
		pedidoRepo.delete(pedido);

		Pedido pedidoContinuaExistindo = buscarPedidoPorId(pedido.getNumeroMatriculaPedido());

		if (pedidoContinuaExistindo == null) {
			return true;
		}
		return false;

	}
}
