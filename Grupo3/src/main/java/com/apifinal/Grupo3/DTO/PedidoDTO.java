package com.apifinal.Grupo3.DTO;

import java.util.Date;
import java.util.List;

public class PedidoDTO {

	private Integer pedidoId;
	private Date dataPedido;
	private Double valorTotal;
	private List<ItemPedidoDTO> itens;

	public Integer getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItemPedidoDTO> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedidoDTO> itens) {
		this.itens = itens;
	}

	public PedidoDTO(Integer pedidoId, Date dataPedido, Double valorTotal, List<ItemPedidoDTO> itens) {
		this.pedidoId = pedidoId;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
		this.itens = itens;
	}

	public PedidoDTO() {
	}

}
