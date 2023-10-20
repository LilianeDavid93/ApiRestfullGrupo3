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

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

		@GetMapping
		public ResponseEntity<List<Endereco>> listarEnderecos() {
			return new ResponseEntity<>(enderecoService.listarEnderecos(), HttpStatus.OK);
		}

		@GetMapping("/{id}")
		public ResponseEntity<Endereco> buscarEnderecoId(@PathVariable Integer id) {
			Endereco endereco = enderecoService.buscarEnderecoId(id);
			if (endereco == null) {
				return new ResponseEntity<>(endereco, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(endereco, HttpStatus.OK);
			}
		}

		@GetMapping("/resumido/{id}")
		public ResponseEntity<EnderecoResumidoDTO> getEnderecoResumido(@PathVariable Integer id) {
			EnderecoResumidoDTO enderecoResDTO = enderecoService.getEnderecoResumido(id);
			if (enderecoResDTO == null) {
				return new ResponseEntity<>(enderecoResDTO, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(enderecoResDTO, HttpStatus.OK);
			}
		}

		@GetMapping("/resumido")
		public ResponseEntity<List<EnderecoResumidoDTO>> listarEnderecosResumidos() {
			return new ResponseEntity<>(enderecoService.listarEnderecosResumidos(), HttpStatus.OK);
		}

		@PostMapping
		public ResponseEntity<Endereco> salvar(@RequestBody Endereco endereco) {
			return new ResponseEntity<>(enderecoService.salvarEndereco(endereco), HttpStatus.CREATED);
		}

		@PutMapping
		public ResponseEntity<Endereco> atualizar(@RequestBody Endereco endereco) {
			return new ResponseEntity<>(enderecoService.atualizarEndereco(endereco), HttpStatus.OK);
		}

		@DeleteMapping
		public ResponseEntity<String> deletarEndereco(@RequestBody Endereco endereco) {
			if (enderecoService.deletarEndereco(endereco))
				return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
			else
				return new ResponseEntity<>("Nao foi possivel deletar", HttpStatus.BAD_REQUEST);
		}
	}

