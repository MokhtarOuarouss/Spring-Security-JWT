package com.mokhtar.spring_security.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_User")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            nullable = false,
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            columnDefinition = "TEXT"
    )
    private String firstName;

    @Column(
            name = "lastName",
            columnDefinition = "TEXT"
    )
    private String lastName;

    @Column(
            name = "Email",
            unique = true,
            columnDefinition = "TEXT",
            updatable = false
    )
    private String email;

    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Adjust as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Adjust as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Adjust as needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Adjust as needed
    }

    // Add your getters and setters here using your IDE's generation feature
}
