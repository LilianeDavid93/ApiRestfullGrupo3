package com.apifinal.Grupo3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifinal.Grupo3.entities.Cliente;
import com.apifinal.Grupo3.repositories.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRep;
	
	public List<Cliente> listarClientes() {
		return clienteRep.findAll();
	}
	
	public Cliente buscarClienteId(Integer clienteId) {
		return clienteRep.findById(clienteId).orElse(null);
	}

	public Cliente salvarCliente(Cliente cliente) {
		return clienteRep.save(cliente);
	}

	public Cliente atualizarCliente(Cliente cliente) {
		return clienteRep.save(cliente);
	}

	public Boolean deletarCliente(Cliente cliente) {
		if (cliente == null) {
			return false;	
		}

		Cliente clienteExistente = buscarClienteId(cliente.getClienteId());
		if (clienteExistente == null) {
			return false;
		}

		clienteRep.delete(cliente);

		Cliente clienteContinuaExistindo = buscarClienteId(cliente.getClienteId());
		if (clienteContinuaExistindo == null) {
			return true;			
		}
		return false;
	}

}
