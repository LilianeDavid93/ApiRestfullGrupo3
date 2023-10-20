package com.apifinal.Grupo3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifinal.Grupo3.entities.Endereco;
import com.apifinal.Grupo3.repositories.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRep;

	public Endereco buscarEnderecoId(Integer enderecoId) {
		return enderecoRep.findById(enderecoId).orElse(null);
	}

	public List<Endereco> listarEndereco() {
		return enderecoRep.findAll();
	}

	public Endereco salvarEndereco(Endereco endereco) {
		return enderecoRep.save(endereco);
	}

	public Endereco atualizarEndereco(Endereco endereco) {
		return enderecoRep.save(endereco);
	}

	public Boolean deletarEndereco(Endereco endereco) {
		if (endereco == null)
			return false;

		Endereco enderecoExistente = buscarEnderecoId(endereco.getEnderecoId());
		if (enderecoExistente == null)
			return false;

		enderecoRep.delete(endereco);

		Endereco enderecoContinuaExistindo = buscarEnderecoId(endereco.getEnderecoId());
		if (enderecoContinuaExistindo == null)
			return true;

		return false;
	}

}