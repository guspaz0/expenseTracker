package com.henry.expenseTracker.service.impl;

import com.henry.expenseTracker.entity.User;
import com.henry.expenseTracker.repository.UserRepository;
import com.henry.expenseTracker.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(User user) {
        Optional<User> optionalUser = this.findById(user.getId());
        if (optionalUser.isPresent()) {
            userRepository.save(user);
        }
    }
}
