package com.example.EtsyProject.EtsyProject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class User implements UserDetails {
    @Column(name="name")
    @NotBlank(message = "username is mandatory")
    private String name;

    @Id
    @Column(name="email")
    @NotBlank(message = "email is mandatory")
    private String email;

    @Column(name="password")
    @NotBlank(message = "password is mandatory")
    private String password;

    @Column(name="dateofbirth")
    private Date dateOfBirth;

    @Column(name="picture")
    private String picture;

    @Column(name="mobile")
    private String mobile;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

    @Column(name="address")
    private String address;

    @Column(name="gender")
    private String gender;

    @Column(name="shopname")
    private String shopName;

    @Column(name="shopimage")
    private String shopImage;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
