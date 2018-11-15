package com.github.bysrkh.rmsboot.service.impl;

import com.github.bysrkh.rmsboot.util.error.DuplicateEntityException;
import com.github.bysrkh.rmsboot.util.error.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Locale;

import com.github.bysrkh.rmsboot.domain.User;
import com.github.bysrkh.rmsboot.repository.UserRepository;
import com.github.bysrkh.rmsboot.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private MessageSource messageSource;

    @Transactional
    @Override
    public void createUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername())
                .orElse(null);

        if(existingUser != null)
            throw new DuplicateEntityException(
                    messageSource.getMessage(
                        "error.entity.duplicate",
                        new Object[]{user.getClass().getSimpleName(),
                        user.getUsername()}, Locale.ENGLISH
                    ),
                    user.getClass().getSimpleName(), user.getUsername()
            );

            userRepository.save(user);
    }

    @Override
    public User findUser(int id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                                String.format("%s with username: %s is not found.",
                                        User.class.getSimpleName(), id)
                        )
                );
    }

    @Override
    public User findUser(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("%s with username: %s is not found.",
                                User.class.getSimpleName(), username)
                        )
                );
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("%s with username: %s is not found.",
                                user.getClass().getSimpleName(), user.getUsername())
                )
        );

        if (existingUser.equals(user))
            userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(int id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("%s with id: %s is not found.",
                                User.class.getSimpleName(), id)
                        )
                );

        if(existingUser != null)
            userRepository.delete(existingUser);
    }

    @Override
    public List<User> findAll() {

        return (List<User>) userRepository.findAll();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
