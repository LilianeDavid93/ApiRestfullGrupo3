package com.apifinal.Grupo3.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apifinal.Grupo3.DTO.ItemPedidoDTO;
import com.apifinal.Grupo3.DTO.PedidoDTO;
import com.apifinal.Grupo3.entities.ItemPedido;
import com.apifinal.Grupo3.repositories.ItemPedidoRepository;

@Service
public class ItemPedidoService {

    @Autowired
    ItemPedidoRepository itemPedidoRepository;
    
    @Autowired
	EmailService emailService;

    public List<ItemPedido> listarItemPedidos() {
        return itemPedidoRepository.findAll();
    }

    public ItemPedido buscarItemPedidoId(Integer itemPedidoId) {
        return itemPedidoRepository.findById(itemPedidoId).orElse(null);
    }

    public ItemPedido salvarItemPedido(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public ItemPedido atualizarItemPedido(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public Boolean deletarItemPedido(ItemPedido itemPedido) {
        if (itemPedido == null) {
            return false;
        }

        ItemPedido itemPedidoExistente = buscarItemPedidoId(itemPedido.getItemPedidoId());
        if (itemPedidoExistente == null) {
            return false;
        }

        itemPedidoRepository.delete(itemPedido);

        ItemPedido itemPedidoContinuaExistindo = buscarItemPedidoId(itemPedido.getItemPedidoId());
        if (itemPedidoContinuaExistindo == null) {
            return true;
        }
        return false;
    }
    
    public String construirRelatorioTexto(List<PedidoDTO> pedidos) {
        StringBuilder relatorioTexto = new StringBuilder();
        
        for (PedidoDTO pedidoDTO : pedidos) {
            relatorioTexto.append("Número do Pedido: ").append(pedidoDTO.getPedidoId()).append("\n");
            relatorioTexto.append("Data do Pedido: ").append(pedidoDTO.getDataPedido()).append("\n");
            relatorioTexto.append("Valor Total: ").append(pedidoDTO.getValorTotal()).append("\n");

            List<ItemPedidoDTO> itensPedido = pedidoDTO.getItensPedido();
            for (ItemPedidoDTO itemPedidoDTO : itensPedido) {
                relatorioTexto.append("  - Item: ").append(itemPedidoDTO.getNome()).append("\n");
                relatorioTexto.append("    Preço de Venda: ").append(itemPedidoDTO.getPrecoVenda()).append("\n");
                relatorioTexto.append("    Quantidade: ").append(itemPedidoDTO.getQuantidade()).append("\n");
                relatorioTexto.append("    Valor Bruto: ").append(itemPedidoDTO.getValorBruto()).append("\n");
                relatorioTexto.append("    Percentual de Desconto: ").append(itemPedidoDTO.getPercentualDesconto()).append("\n");
                relatorioTexto.append("    Valor Líquido: ").append(itemPedidoDTO.getValorLiquido()).append("\n");
            }
            
            // Adicione separadores, como linhas em branco, para melhorar a legibilidade
            relatorioTexto.append("\n");
        }
        
        return relatorioTexto.toString();
    }
   
    public PedidoDTO construirRelatorio(ItemPedido itemPedido) {
        PedidoDTO relatorio = new PedidoDTO();
        relatorio.setPedidoId(itemPedido.getItemPedidoId()); 
        return relatorio;
    }

  
    public String construirRelatorioTexto(PedidoDTO relatorio) {
        StringBuilder relatorioTexto = new StringBuilder();

        relatorioTexto.append("Número do Pedido: ").append(relatorio.getPedidoId()).append("\n");
        relatorioTexto.append("Data do Pedido: ").append(relatorio.getDataPedido()).append("\n");
        relatorioTexto.append("Valor Total: ").append(relatorio.getValorTotal()).append("\n");

        List<ItemPedidoDTO> itensPedido = relatorio.getItensPedido();
        for (ItemPedidoDTO itemPedidoDTO : itensPedido) {
            relatorioTexto.append("  - Item: ").append(itemPedidoDTO.getNome()).append("\n");
            relatorioTexto.append("    Preço de Venda: ").append(itemPedidoDTO.getPrecoVenda()).append("\n");
            relatorioTexto.append("    Quantidade: ").append(itemPedidoDTO.getQuantidade()).append("\n");
            relatorioTexto.append("    Valor Bruto: ").append(itemPedidoDTO.getValorBruto()).append("\n");
            relatorioTexto.append("    Percentual de Desconto: ").append(itemPedidoDTO.getPercentualDesconto()).append("\n");
            relatorioTexto.append("    Valor Líquido: ").append(itemPedidoDTO.getValorLiquido()).append("\n");
        }

      
        relatorioTexto.append("\n");

        return relatorioTexto.toString();
    }

   
}
