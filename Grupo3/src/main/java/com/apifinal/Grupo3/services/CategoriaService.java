package com.apifinal.Grupo3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifinal.Grupo3.entities.Categoria;
import com.apifinal.Grupo3.exceptions.CategoriaNotFoundException;
import com.apifinal.Grupo3.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRep;

	public Categoria buscarCategoriaId(Integer categoriaId) {
		return categoriaRep.findById(categoriaId)
		        .orElseThrow(() -> new CategoriaNotFoundException(categoriaId));
	}

	public List<Categoria> listarCategorias() {
		return categoriaRep.findAll();
	}

	public Categoria salvarCategoria(Categoria categoria) {
		return categoriaRep.save(categoria);
	}

	public Categoria atualizarCategoria(Categoria categoria) {
		return categoriaRep.save(categoria);
	}

	public Boolean deletarCategoria(Categoria categoria) {
		if (categoria == null)
			return false;

		Categoria categoriaExistente = buscarCategoriaId(categoria.getCategoriaId());
		if (categoriaExistente == null)
			return false;

		categoriaRep.delete(categoria);

		return true;
	}
}