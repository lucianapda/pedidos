package com.challenge.orderManager.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.orderManager.entities.Pedido;
import com.challenge.orderManager.entities.QPedido;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class PedidoRepositoryQueriesImpl implements PedidoRepositoryQueries {

//	private static final QPedido PEDIDO = QPedido.pedido;
	private static final QPedido PEDIDOX = QPedido.pedido;

	@Autowired
	private EntityManager entityManager;

	@Override
	public Pedido getPedido(long pedidoId) {
		return new JPAQueryFactory(entityManager).selectFrom(PEDIDOX).where(PEDIDOX.id.eq(pedidoId)).fetchFirst();
	}

}
