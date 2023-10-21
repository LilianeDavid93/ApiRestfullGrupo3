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
	PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<Pedido>> listarPedidos() {
		return new ResponseEntity<>(pedidoService.listarPedidos(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscarPedidoId(@PathVariable Integer id) {
		Pedido pedido = pedidoService.buscarPedidoPorId(id);
		if (pedido == null) {
			return new ResponseEntity<>(pedido, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(pedido, HttpStatus.OK);
		}
	}

//	@PostMapping
//	public ResponseEntity<Pedido> salvarPedido(@RequestBody Pedido pedido) {
//		return new ResponseEntity<>(pedidoService.salvarPedido(pedido), HttpStatus.CREATED);
//	}

	@PostMapping("/cadastro")
	public ResponseEntity<?> salvarPedido(@RequestBody Pedido pedido) {
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

	    return new ResponseEntity<>(pedidoSalvo, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Pedido> atualizar(@RequestBody Pedido pedido) {
		return new ResponseEntity<>(pedidoService.atualizarPedido(pedido), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deletarPedido(@RequestBody Pedido pedido) {
		if (Boolean.TRUE.equals(pedidoService.deletarPedido(pedido)) == true)
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
		else
			return new ResponseEntity<>("Nao foi possivel deletar", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/relatorio")
	public ResponseEntity<List<PedidoDTO>> gerarRelatorioDePedidos() {
		List<PedidoDTO> relatorio = pedidoService.listarPedidosComItens();

		if (relatorio.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(relatorio);
	}

	@GetMapping("/relatorio/{id}")
	public ResponseEntity<PedidoDTO> RelatorioPedido(@PathVariable Integer id) {
		PedidoDTO pedidoDTO = pedidoService.PedidoRelatorioPorId(id);
		if (pedidoDTO == null) {
			return new ResponseEntity<>(pedidoDTO, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);
		}
	}
}
