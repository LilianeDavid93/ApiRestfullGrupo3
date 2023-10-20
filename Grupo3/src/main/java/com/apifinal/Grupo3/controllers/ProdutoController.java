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

import com.apifinal.Grupo3.entities.Produto;
import com.apifinal.Grupo3.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> listarProduto() {
		return new ResponseEntity<>(produtoService.listarProduto(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarProdutoId(@PathVariable Integer id) {
		Produto produto = produtoService.buscarProdutoId(id);
		if (produto == null) {
			return new ResponseEntity<>(produto, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(produto, HttpStatus.OK);
		}
	}

	@PostMapping
	public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.salvarProduto(produto), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Produto> atualizar(@RequestBody Produto produto) {
		return new ResponseEntity<>(produtoService.atualizarProduto(produto), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deletarProduto(@RequestBody Produto produto) {
		if (produtoService.deletarProduto(produto))
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
		else
			return new ResponseEntity<>("Nao foi possivel deletar", HttpStatus.BAD_REQUEST);
	}
}