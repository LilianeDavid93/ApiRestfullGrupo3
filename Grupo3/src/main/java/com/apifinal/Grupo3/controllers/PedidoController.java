package com.apifinal.Grupo3.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apifinal.Grupo3.DTO.PedidoDTO;
import com.apifinal.Grupo3.entities.Pedido;
import com.apifinal.Grupo3.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> listarPedidos() {
		List<Pedido> pedidos = pedidoService.listarPedidos();

		if (pedidos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(pedidos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Integer id) {
		Pedido pedido = pedidoService.buscarPedidoPorId(id);
		return new ResponseEntity<>(pedido, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Pedido> atualizar(@RequestBody Pedido pedido) {
		return new ResponseEntity<>(pedidoService.atualizarPedido(pedido), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deletarPedido(@RequestBody Pedido pedido) {
		if (pedidoService.deletarPedido(pedido))
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
		else
			return new ResponseEntity<>("Nao foi possivel deletar", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/DTO")
	public ResponseEntity<List<PedidoDTO>> listarPedidosDTO() {
		List<PedidoDTO> pedidos = pedidoService.listarPedidosComItens();

		if (pedidos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(pedidos);
	}

	@PostMapping
	public ResponseEntity<String> salvarPedido(@RequestBody PedidoDTO pedidoDTO) {
		Pedido pedido = pedidoService.convertToEntity(pedidoDTO);

		if (pedido == null) {
			return ResponseEntity.badRequest().build();
		}

		if (pedido.getDataPedido() == null) {
			return ResponseEntity.badRequest().body("A data do pedido é obrigatória.");
		}

		if (pedido.getDataEntrega() != null && pedido.getDataEntrega().before(pedido.getDataPedido())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("A data de entrega não pode ser anterior à data de cadastro.");
		}
		Date dataPedidoDate = pedido.getDataPedido();
		LocalDateTime dataPedido = dataPedidoDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		if (!pedidoService.validarDataPedido(dataPedido)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Não é possível cadastrar um pedido com data retroativa.");
		}
		Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);

		PedidoDTO pedidoSalvoDTO = pedidoService.convertToDTO(pedidoSalvo);

		return ResponseEntity.status(HttpStatus.CREATED).body("Pedido realizado com sucesso");
	}
}