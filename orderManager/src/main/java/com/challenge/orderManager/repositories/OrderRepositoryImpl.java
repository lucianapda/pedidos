package com.challenge.orderManager.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.orderManager.entities.Order;

import com.challenge.orderManager.entities.QOrder;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class OrderRepositoryImpl implements OrderRepositoryQueries {
	private static final QOrder ORDER = QOrder.order;	

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Order getOrder(int orderId) {
		return new JPAQueryFactory( entityManager )
				.selectFrom( ORDER )
				.where(ORDER.id.eq(orderId))
				.fetchFirst();		
	}

	@Override
	public List<Order> getOrderList() {
		return new JPAQueryFactory( entityManager )
				.selectFrom(ORDER ).fetch();
	}

}



