package com.apifinal.Grupo3.services;

import java.io.IOException;
import java.util.List;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.apifinal.Grupo3.entities.Produto;
import com.apifinal.Grupo3.exceptions.ProdutoNotFoundException;
import com.apifinal.Grupo3.repositories.ProdutoRepository;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRep;

	public Produto buscarProdutoId(Integer produtoId) {
		return produtoRep.findById(produtoId)
		        .orElseThrow(() -> new ProdutoNotFoundException(produtoId));
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
	
	public Produto salvarProdutoComFoto(String strProduto, MultipartFile arqImg)
			throws IOException {
		Produto produto = new Produto();

		try {
			ObjectMapper objMp = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			produto = objMp.readValue(strProduto, Produto.class);
		} catch (IOException e) {
			System.out.println("Erro aoa converter a string Editora: " + e.toString());
		}

		produto.setImagem(arqImg.getBytes());

		return produtoRep.save(produto);
	}
}
