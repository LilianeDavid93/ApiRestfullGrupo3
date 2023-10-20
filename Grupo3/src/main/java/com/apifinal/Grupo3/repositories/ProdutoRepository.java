package com.apifinal.Grupo3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apifinal.Grupo3.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

}
