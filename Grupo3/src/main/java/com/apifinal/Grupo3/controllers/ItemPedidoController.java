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
import com.apifinal.Grupo3.entities.ItemPedido;
import com.apifinal.Grupo3.services.EmailService;
import com.apifinal.Grupo3.services.ItemPedidoService;

@RestController
@RequestMapping("/itemPedidos")
public class ItemPedidoController {

    @Autowired
    ItemPedidoService itemPedidoService;
    
    @Autowired
	EmailService emailService;

    @GetMapping
    public ResponseEntity<List<ItemPedido>> listarItemPedidos() {
        return new ResponseEntity<>(itemPedidoService.listarItemPedidos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> buscarItemPedidoId(@PathVariable Integer id) {
        ItemPedido itemPedido = itemPedidoService.buscarItemPedidoId(id);
        if (itemPedido == null) {
            return new ResponseEntity<>(itemPedido, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(itemPedido, HttpStatus.OK);
        }
    }
//
//    @PostMapping
//    public ResponseEntity<ItemPedido> salvar(@RequestBody ItemPedido itemPedido) {
//        ItemPedido itemPedidoSalvo = itemPedidoService.salvarItemPedido(itemPedido);
//        
//     
//		emailService.enviarEmail("apiEcommercebr@gmail.com", "Relatorio Cadastro de Pedido", itemPedido.toString());
//       
//        
//        return new ResponseEntity<>(itemPedidoSalvo, HttpStatus.CREATED);
//    }
//    
  
    @PostMapping
    public ResponseEntity<ItemPedido> salvarEEnviarRelatorio(@RequestBody ItemPedido itemPedido) {
     
     

        ItemPedido itemPedidoSalvo = itemPedidoService.salvarItemPedido(itemPedido);

        PedidoDTO relatorio = itemPedidoService.construirRelatorio(itemPedidoSalvo);

        String relatorioTexto = itemPedidoService.construirRelatorioTexto(relatorio);

      
         emailService.enviarEmail("apiEcommercebr@gmail.com", "Relatório de Pedido", relatorioTexto);

       
        return new ResponseEntity<>(itemPedidoSalvo, HttpStatus.CREATED);
    }

  





 
    @PutMapping
    public ResponseEntity<ItemPedido> atualizar(@RequestBody ItemPedido itemPedido) {
        return new ResponseEntity<>(itemPedidoService.atualizarItemPedido(itemPedido), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deletarItemPedido(@RequestBody ItemPedido itemPedido) {
        if (itemPedidoService.deletarItemPedido(itemPedido))
            return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
        else
            return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
    }
}