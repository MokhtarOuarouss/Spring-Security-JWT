package com.mokhtar.spring_security.serviceImp;

import com.mokhtar.spring_security.Entity.User;
import com.mokhtar.spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    private com.mokhtar.spring_security.repository.UserRepository UserRepository;
    private PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User User) {
        //User.setPassword(passwordEncoder.encode(User.getPassword()));
        return UserRepository.save(User);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return UserRepository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return UserRepository.findByEmail(email);

    }

    @Override
    public void DeleteUser(Long id) {
        UserRepository.deleteById(id);

    }
}
