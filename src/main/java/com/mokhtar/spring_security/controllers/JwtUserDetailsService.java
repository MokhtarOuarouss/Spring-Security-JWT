package com.mokhtar.spring_security.controllers;


import com.mokhtar.spring_security.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JwtUserDetailsService(UserRepository UserRepository) {
        this.userRepository = UserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    /*@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Load the User entity from your repository based on the provided username
        User User = UserRepository.findByEmail(email);

        if (User != null) {
            // Create a UserDetails object based on the retrieved User entity
            return new org.springframework.security.core.userdetails.User(
                    User.getEmail(),
                    User.getPassword(), // Assuming the password is already hashed
                    new ArrayList<>() // Add roles/authorities here if needed
            );
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }*/
}