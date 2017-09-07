package com.challenge.orderManager.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.challenge.orderManager.entities.QUser;
import com.challenge.orderManager.entities.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class UserRepositoryImpl implements UserRepositoryQueries{
	
	private final QUser USER = QUser.user;	

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<User> getUserList() {
		 return new JPAQueryFactory( entityManager )
		.selectFrom( USER ).fetch();
	}

	@Override
	public User findByEmail(String email) {
		return new JPAQueryFactory( entityManager )
				.selectFrom( USER )
				.where(USER.email.eq(email))
				.fetchFirst();
	}

}
