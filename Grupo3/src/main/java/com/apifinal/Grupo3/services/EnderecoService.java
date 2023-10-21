package com.apifinal.Grupo3.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.apifinal.Grupo3.DTO.CepDTO;
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

	public CepDTO consultaCep(String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://viacep.com.br/ws/{cep}/json";

		Map<String, String> params = new HashMap<String, String>();
		params.put("cep", cep);

		CepDTO cepDto = restTemplate.getForObject(uri, CepDTO.class, params);

		return cepDto;
	}
}