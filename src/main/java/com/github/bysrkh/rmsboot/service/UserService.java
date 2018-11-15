package com.github.bysrkh.rmsboot.service;

import com.github.bysrkh.rmsboot.domain.User;

import java.util.List;

public interface UserService {

    void createUser(User user);

    User findUser(int username);

    User findUser(String username);

    void updateUser(User user);

    void deleteUser(int id);

    List<User> findAll();
}
