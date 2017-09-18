package com.challenge.orderManager.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.orderManager.entities.Product;
import com.challenge.orderManager.entities.QProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * Classe que implementa querys elaboradas
 * @author Adailton Hafemann
 *
 */
public class ProductRepositoryImpl implements ProductRepositoryQueries {

	private static final QProduct PRODUCT = QProduct.product;	

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Product getProduct(int productId) {
		return new JPAQueryFactory( entityManager )
				.selectFrom( PRODUCT )
				.where(PRODUCT.id.eq(productId))
				.fetchFirst();				
	}

	@Override
	public List<Product> getProductList() {
		return new JPAQueryFactory( entityManager )
				.selectFrom( PRODUCT ).fetch();
	}

}
