package com.apifinal.Grupo3.DTO;

import java.util.Date;
import java.util.List;

public class PedidoDTO {

	private Integer pedidoId;
	private Date dataPedido;
	private Double valorTotal;
	private List<ItemPedidoDTO> itensPedido;
	
	
	public PedidoDTO() {
	
	}

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

	public List<ItemPedidoDTO> getItensPedido() {
		return itensPedido;
	}

	public void setItensPedido(List<ItemPedidoDTO> itensPedido) {
		this.itensPedido = itensPedido;
	}

	public PedidoDTO(Integer pedidoId, Date dataPedido, Double valorTotal, List<ItemPedidoDTO> itensPedido) {
		super();
		this.pedidoId = pedidoId;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
		this.itensPedido = itensPedido;
	}

	



	
	
	
}
