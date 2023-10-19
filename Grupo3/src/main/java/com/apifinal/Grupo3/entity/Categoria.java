package com.apifinal.Grupo3.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "categoriaId",
		scope = Produto.class
		)
@Entity
@Table(name = "categoria")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoriaid")
	private Integer categoriaId;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descricao", unique = true)
	private String descricao;
	
	/* REVISAR
	 
	  @ManyToOne
	@JoinColumn(name = "categoriaid", referencedColumnName = "categoriaid")
	private Categoria categoria;
	
	*/

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}