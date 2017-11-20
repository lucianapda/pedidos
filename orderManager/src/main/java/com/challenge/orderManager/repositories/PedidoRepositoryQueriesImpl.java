package com.challenge.orderManager.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.orderManager.entities.Pedido;
import com.challenge.orderManager.entities.QPedido;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class PedidoRepositoryQueriesImpl implements PedidoRepositoryQueries {

	private static final QPedido PEDIDOX = QPedido.pedido;

	@Autowired
	private EntityManager entityManager;

	@Override
	public Pedido getPedido(int mesaId) {
		return new JPAQueryFactory(entityManager).selectFrom(PEDIDOX).where(PEDIDOX.mesa.eq(mesaId).and(PEDIDOX.pago.eq(0))).fetchFirst();
	}		

}
