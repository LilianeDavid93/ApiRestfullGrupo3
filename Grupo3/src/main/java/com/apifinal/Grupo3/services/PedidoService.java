package com.apifinal.Grupo3.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifinal.Grupo3.DTO.ItemPedidoDTO;
import com.apifinal.Grupo3.DTO.PedidoDTO;
import com.apifinal.Grupo3.entities.ItemPedido;
import com.apifinal.Grupo3.entities.Pedido;
import com.apifinal.Grupo3.exceptions.PedidoNotFoundException;
import com.apifinal.Grupo3.repositories.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepo;

    @Autowired
    EmailService emailService;

    public List<Pedido> listarPedidos() {
        return pedidoRepo.findAll();
    }

    public Pedido buscarPedidoPorId(Integer pedidoId) {
    	return pedidoRepo.findById(pedidoId)
		        .orElseThrow(() -> new PedidoNotFoundException(pedidoId));
    }

    public List<PedidoDTO> listarPedidosComItens() {
        List<Pedido> pedidos = pedidoRepo.findAll();
        List<PedidoDTO> pedidosDTO = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            PedidoDTO pedidoDTO = convertToDTO(pedido);
            pedidosDTO.add(pedidoDTO);
        }

        return pedidosDTO;
    }

    public Pedido salvarPedido(Pedido pedido) {
    	Date dataAtual = new Date(-1);
    	Pedido cadastroPedido = pedidoRepo.save(pedido);
		
    	if(pedido.getDataPedido() != null && pedido.getDataPedido().before(dataAtual)) {
			throw new IllegalArgumentException("A data do pedido não pode ser retroativa.");
		}

        String relatorio = criarRelatorio(cadastroPedido);
        emailService.enviarEmail("apiEcommercebr@gmail.com", "Relatorio Cadastro de Pedido",  relatorio);
        return cadastroPedido;
    }

   

    public Pedido atualizarPedido(Pedido pedido) {
        return pedidoRepo.save(pedido);
    }

    public Boolean deletarPedido(Pedido pedido) {
        if (pedido == null) {
            return false;
        }
        Pedido pedidoExistente = buscarPedidoPorId(pedido.getPedidoId());

        if (pedidoExistente == null) {
            return false;
        }
        pedidoRepo.delete(pedido);

        Pedido pedidoContinuaExistindo = buscarPedidoPorId(pedido.getPedidoId());

        if (pedidoContinuaExistindo == null) {
            return true;
        }
        return false;
    }
    
	public boolean validarDataPedido(LocalDateTime dataPedido) {
		LocalDateTime dataAtual = LocalDateTime.now();
		return !dataPedido.isBefore(dataAtual);
	}
    
	public double calcularValorTotalPedido(List<ItemPedido> itensPedido) {
	    double valorTotal = 0.0;
	    for (ItemPedido item : itensPedido) {
	        valorTotal += item.getValorLiquido();
	    }
	    return valorTotal;
	}

    
    public Pedido convertToEntity(PedidoDTO pedidoDTO) {
        if (pedidoDTO == null) {
            return null; 
        }

        Pedido pedido = new Pedido();
        pedido.setPedidoId(pedidoDTO.getPedidoId());
        pedido.setDataPedido(pedidoDTO.getDataPedido());

        List<ItemPedido> itens = new ArrayList<>();
        double valorTotal = 0.0;

        for (ItemPedidoDTO itemDTO : pedidoDTO.getItensPedido()) {
            ItemPedido item = new ItemPedido();
            item.setQuantidade(itemDTO.getQuantidade());

            double precoVenda = itemDTO.getPrecoVenda();
            double percentualDesconto = itemDTO.getPercentualDesconto();
            double valorBruto = precoVenda * item.getQuantidade();
            double valorLiquido = valorBruto - (valorBruto * (percentualDesconto / 100));

            item.setValorBruto(valorBruto);
            item.setValorLiquido(valorLiquido);
            item.setPercentualDesconto(percentualDesconto);

           
            itens.add(item);
            valorTotal += valorLiquido;
        }

        pedido.setItensPedidos(itens);
        pedido.setValorTotal(valorTotal);

        return pedido;
    }

    public PedidoDTO convertToDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setPedidoId(pedido.getPedidoId());
        pedidoDTO.setDataPedido(pedido.getDataPedido());
        pedidoDTO.setValorTotal(pedido.getValorTotal());

        List<ItemPedidoDTO> itensDTO = new ArrayList<>();
        for (ItemPedido itemPedido : pedido.getItensPedidos()) {
            ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
            itemPedidoDTO.setItemPedidoId(itemPedido.getItemPedidoId());
        //    itemPedidoDTO.setNome(itemPedido.getProduto().getNome());
           //	itemPedidoDTO.setPrecoVenda(itemPedido.getProduto().getValorUnitario());
            itemPedidoDTO.setQuantidade(itemPedido.getQuantidade());
            itemPedidoDTO.setValorBruto(itemPedido.getValorBruto());
            itemPedidoDTO.setPercentualDesconto(itemPedido.getPercentualDesconto());
            itemPedidoDTO.setValorLiquido(itemPedido.getValorLiquido());
            itensDTO.add(itemPedidoDTO);
        }

        pedidoDTO.setItensPedido(itensDTO);

        return pedidoDTO;
    }
    
    
    private String criarRelatorio(Pedido pedido) {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Pedido\n\n");
        relatorio.append("ID do Pedido: " + pedido.getPedidoId() + "\n");
        relatorio.append("Data do Pedido: " + pedido.getDataPedido() + "\n");
        relatorio.append("Valor Total: " + pedido.getValorTotal() + "\n\n");
        relatorio.append("Itens do Pedido:\n");

        for (ItemPedido item : pedido.getItensPedidos()) {
            relatorio.append("Nome do Produto: " + item.getItemPedidoId() + "\n");
            relatorio.append("Preço de Venda: " + item.getPrecoVenda() + "\n");
            relatorio.append("Quantidade: " + item.getQuantidade() + "\n");
            relatorio.append("Valor Bruto: " + item.getValorBruto() + "\n");
            relatorio.append("Percentual de Desconto: " + item.getPercentualDesconto() + "\n");
            relatorio.append("Valor Líquido: " + item.getValorLiquido() + "\n\n");
        }

        return relatorio.toString();
    }
    
}