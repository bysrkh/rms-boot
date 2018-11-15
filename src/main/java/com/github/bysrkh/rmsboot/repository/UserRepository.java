package com.github.bysrkh.rmsboot.repository;

import org.springframework.data.repository.CrudRepository;

import com.github.bysrkh.rmsboot.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    default Optional<User> findById(int id) {

        return Optional.of(findOne(id));
    }

    List<User> findAll();
}
