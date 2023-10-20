package com.apifinal.Grupo3.controllers;

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
		public ResponseEntity<PedidoDTO> buscarPedidoId(@PathVariable Integer id) {
			Pedido pedido = pedidoService.buscarPedidoPorId(id);
			if (pedido == null) {
				return new ResponseEntity<>(pedido, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(pedido, HttpStatus.OK);
			}
		}

		@GetMapping("/relatorio/{id}")
		public ResponseEntity<PedidoDTO> RelatorioPedido(@PathVariable Integer id) {
			PedidoDTO pedidoDTO = pedidoService..getPedidoResumido(id);
			if (pedidoDTO == null) {
				return new ResponseEntity<>(pedidoResDTO, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(pedidoResDTO, HttpStatus.OK);
			}
		}

		@GetMapping("/relatorio")
		public ResponseEntity<List<PedidoDTO>> listarPedidosRelatorios() {
			return new ResponseEntity<>(pedidoService..listarPedidosResumidos(), HttpStatus.OK);
		}

		@PostMapping
		public ResponseEntity<Pedido> salvar(@RequestBody Pedido pedido) {
			return new ResponseEntity<>(pedidoService.salvarPedido(pedido), HttpStatus.CREATED);
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
	}

