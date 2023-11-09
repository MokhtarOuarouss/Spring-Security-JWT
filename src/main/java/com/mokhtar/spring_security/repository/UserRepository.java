package com.mokhtar.spring_security.repository;

import com.mokhtar.spring_security.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
