package com.apifinal.Grupo3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apifinal.Grupo3.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
