package com.apifinal.Grupo3.DTO;

import java.util.Date;

public class ClienteDto {

	private Integer pedidoId;
	private Date dataPedido;
	private Double valorTotal;

	public ClienteDto(Integer pedidoId, Date dataPedido, Double valorTotal) {
		super();
		this.pedidoId = pedidoId;
		this.dataPedido = dataPedido;
		this.valorTotal = valorTotal;
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

}
