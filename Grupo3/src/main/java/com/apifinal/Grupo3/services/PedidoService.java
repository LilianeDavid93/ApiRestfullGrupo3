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

	public List<PedidoDTO> getPedidosResumidos() {
		List<Pedido> pedidos = pedidoRepo.findAll();
		List<PedidoDTO> pedidosDTO = new ArrayList<>();

		for (Pedido pedido : pedidos) {
			PedidoDTO pedidoResDTO = new PedidoDTO();
			pedidoResDTO.setNumeroMatriculaPedido(pedido.getNumeroMatriculaPedido());
			pedidoResDTO.setNome(pedido.getNome());
			pedidoResDTO.setCpf(pedido.getCpf());
			pedidosDTO.add(pedidoResDTO);
		}

		return pedidosDTO;
	}

	public Pedido buscarPedidoPorId(Integer id) {

		return pedidoRepo.findById(id).orElse(null);
	}

	public PedidoDTO getPedidoResumidoPorId(Integer id) {
		Pedido pedido = pedidoRepo.findById(id).orElse(null);

		if (pedido != null) {
			PedidoDTO pedidoResDTO = new PedidoDTO(pedido.getNumeroMatriculaPedido(), pedido.getNome(),
					pedido.getCpf());
			return pedidoResDTO;

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
