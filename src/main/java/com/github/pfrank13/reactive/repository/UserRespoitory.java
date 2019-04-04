package com.github.pfrank13.reactive.repository;

import com.github.pfrank13.reactive.model.User;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author pfrank
 */
public interface UserRespoitory extends ReactiveCrudRepository<User, Integer> {

}
