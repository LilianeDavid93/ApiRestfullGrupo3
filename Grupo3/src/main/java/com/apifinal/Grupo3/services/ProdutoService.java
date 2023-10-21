package com.apifinal.Grupo3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifinal.Grupo3.entities.Produto;
import com.apifinal.Grupo3.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRep;

	public Produto buscarProdutoId(Integer produtoId) {
		return produtoRep.findById(produtoId).orElse(null);
	}

	public List<Produto> listarProduto() {
		return produtoRep.findAll();
	}

	public Produto salvarProduto(Produto produto) {
		return produtoRep.save(produto);
	}

	public Produto atualizarProduto(Produto produto) {
		return produtoRep.save(produto);
	}

	public Boolean deletarProduto(Produto produto) {
		if (produto == null)
			return false;

		Produto produtoExistente = buscarProdutoId(produto.getProdutoId());
		if (produtoExistente == null)
			return false;

		produtoRep.delete(produto);

		Produto produtoContinuaExistindo = buscarProdutoId(produto.getProdutoId());
		if (produtoContinuaExistindo == null)
			return true;

		return false;
	}

}
