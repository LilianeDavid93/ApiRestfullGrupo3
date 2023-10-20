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

import com.apifinal.Grupo3.entities.Cliente;
import com.apifinal.Grupo3.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listar(){
		return new ResponseEntity<>(clienteService.listarClientes(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Integer id) {
		Cliente cliente = clienteService.buscarClienteId(id);
		
		if(cliente == null) {
			return new ResponseEntity<>(cliente, HttpStatus.NOT_FOUND);
		}else
			return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.salvarCliente(cliente), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Cliente> atualizar(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.atualizarCliente(cliente), HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deletar(@RequestBody Cliente cliente) {
		if(clienteService.deletarCliente(cliente) == true) {
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
		}
		else return new ResponseEntity<>("Não foi possível deletar", HttpStatus.BAD_REQUEST);
	}
}
