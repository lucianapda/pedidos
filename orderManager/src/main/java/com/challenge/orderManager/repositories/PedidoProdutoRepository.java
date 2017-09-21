package com.challenge.orderManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.challenge.orderManager.entities.Pedido;
import com.challenge.orderManager.entities.PedidoProduto;

public interface PedidoProdutoRepository extends CrudRepository<PedidoProduto, Long>{

}
