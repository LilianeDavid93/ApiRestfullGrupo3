package com.apifinal.Grupo3.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "itemPedidoId",
		scope = ItemPedido.class
		)
@Entity
@Table(name = "itempedido")
public class ItemPedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itempedidoid")
	private Integer itemPedidoId;
	
	@Column(name = "quantidade")
	private Integer quantidade;
	
	@Column(name = "precovenda")
	private Double precoVenda;
	
	@Column(name = "percentualdesconto")
	private Double percentualDesconto;
	
	@Column(name = "valorbruto")
	private Double valorBruto;

	@Column(name = "valorliquido")
	private Double valorLiquido;
	
	@OneToMany(mappedBy = "itempedido")
	private List<Pedido> pedido;
	
	@OneToMany(mappedBy = "itempedido")
	private List<Produto> produto;

	public Integer getItemPedidoId() {
		return itemPedidoId;
	}

	public void setItemPedidoId(Integer itemPedidoId) {
		this.itemPedidoId = itemPedidoId;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Double getPercentualDesconto() {
		return percentualDesconto;
	}

	public void setPercentualDesconto(Double percentualDesconto) {
		this.percentualDesconto = percentualDesconto;
	}

	public Double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(Double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public Double getValorLiquido() {
		return valorLiquido;
	}

	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}
	
	
}
