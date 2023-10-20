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

import com.apifinal.Grupo3.entities.Categoria;
import com.apifinal.Grupo3.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias() {
		return new ResponseEntity<>(categoriaService.listarCategorias(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
		public ResponseEntity<Categoria> buscarCategoriaId(@PathVariable Integer id) {
			Categoria categoria = categoriaService.buscarCategoriaId(id);
			if (categoria == null) {
				return new ResponseEntity<>(categoria, HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(categoria, HttpStatus.OK);
			}
		}

	@PostMapping
	public ResponseEntity<Categoria> salvar(@RequestBody Categoria categoria) {
		return new ResponseEntity<>(categoriaService.salvarCategoria(categoria), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Categoria> atualizar(@RequestBody Categoria categoria) {
		return new ResponseEntity<>(categoriaService.atualizarCategoria(categoria), HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<String> deletarCategoria(@RequestBody Categoria categoria) {
		if (categoriaService.deletarCategoria(categoria))
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
		else
			return new ResponseEntity<>("Nao foi possivel deletar", HttpStatus.BAD_REQUEST);
	}
}