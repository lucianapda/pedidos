package com.challenge.orderManager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.challenge.orderManager.entities.User;

public interface UserRepository extends UserRepositoryQueries ,CrudRepository<User, String> {

}
