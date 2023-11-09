package com.mokhtar.spring_security.service;

import com.mokhtar.spring_security.Entity.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(Long  id);
    User findByEmail(String email);
    void DeleteUser(Long id);
}
