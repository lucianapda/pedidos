package com.challenge.orderManager.repositories;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.orderManager.entities.Order;
import com.challenge.orderManager.entities.OrderItem;
import com.challenge.orderManager.entities.QOrder;
import com.challenge.orderManager.entities.QOrderItem;
import com.challenge.orderManager.entities.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class OrderItemRepositoryImpl implements OrderItemRepositoryQueries {
	private static final QOrderItem ORDERITEM = QOrderItem.orderItem;	
	private static final QOrder ORDER = QOrder.order;	
	private static final QProduct PRODUCT = QProduct.product;

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public OrderItem getOrderItem(long orderItemId) {
		return new JPAQueryFactory( entityManager )
				.selectFrom( ORDERITEM )
				.where(ORDERITEM.id.eq(orderItemId))
				.fetchFirst();
	}

	@Override
	public List<OrderItem> getOrderItemList(long orderId) {
		return new JPAQueryFactory( entityManager )
				.selectFrom(ORDERITEM )
				.where(ORDERITEM.orderx.order_id.eq(orderId))
				.fetch();
	}


}
