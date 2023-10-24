package com.apifinal.Grupo3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apifinal.Grupo3.DTO.PedidoDTO;
import com.apifinal.Grupo3.entities.Cliente;
import com.apifinal.Grupo3.entities.Endereco;
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
    public ResponseEntity<PedidoDTO> buscarPedidoPorId(@PathVariable Integer id) {
        PedidoDTO pedido = pedidoService.PedidoRelatorioPorId(id);
        if (pedido == null) {
            return new ResponseEntity<>(pedido, HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(pedido);
        }
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
	
	@GetMapping("/DTO")
	public ResponseEntity<List<PedidoDTO>> listarPedidosDTO() {
		List<PedidoDTO> pedidos = pedidoService.listarPedidosComItens();
		
		if (pedidos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(pedidos);
	}

	@PostMapping("/calculo")
	public ResponseEntity<Pedido> calcularPedido(@RequestBody Pedido pedido) {
	    pedidoService.calcularValoresItensPedido(pedido.getItensPedidos());

	    double valorTotal = pedidoService.calcularValorTotalPedido(pedido.getItensPedidos());
	    pedido.setValorTotal(valorTotal);

	    Pedido novoPedido = pedidoService.salvarPedido(pedido);

	    return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
	}
	
    @PostMapping("/DTO")
    public ResponseEntity<PedidoDTO> salvarPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.convertToEntity(pedidoDTO);

        if (pedido == null) {
            return ResponseEntity.badRequest().build(); 
        }

        Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);

        PedidoDTO pedidoSalvoDTO = pedidoService.convertToDTO(pedidoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoSalvoDTO); 
    }
}