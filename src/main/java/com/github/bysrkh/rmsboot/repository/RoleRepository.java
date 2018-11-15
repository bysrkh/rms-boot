package com.github.bysrkh.rmsboot.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.github.bysrkh.rmsboot.domain.Role;

public interface RoleRepository extends CrudRepository<Role, String> {
    List<Role> findAll();
}

