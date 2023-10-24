package com.apifinal.Grupo3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.apifinal.Grupo3.DTO.PedidoDTO;
import com.apifinal.Grupo3.entities.Pedido;
import com.apifinal.Grupo3.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidos() {
        List<PedidoDTO> pedidos = pedidoService.listarPedidosComItens();

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