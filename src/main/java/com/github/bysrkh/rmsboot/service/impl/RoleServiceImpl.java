package com.github.bysrkh.rmsboot.service.impl;

import com.github.bysrkh.rmsboot.domain.Role;
import com.github.bysrkh.rmsboot.repository.RoleRepository;
import com.github.bysrkh.rmsboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {

        return roleRepository.findAll();
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
