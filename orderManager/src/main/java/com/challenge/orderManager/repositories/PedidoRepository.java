package com.challenge.orderManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.challenge.orderManager.entities.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long>{

}
