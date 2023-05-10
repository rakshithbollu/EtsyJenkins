package com.example.EtsyProject.EtsyProject.service;

import com.example.EtsyProject.EtsyProject.controller.AuthenticationResponse;
import com.example.EtsyProject.EtsyProject.controller.LoginRequest;
import com.example.EtsyProject.EtsyProject.controller.RegisterRequest;
import com.example.EtsyProject.EtsyProject.dao.UserRepository;
import com.example.EtsyProject.EtsyProject.entity.Role;
import com.example.EtsyProject.EtsyProject.entity.User;
import com.example.EtsyProject.EtsyProject.security.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService (UserRepository UserRepository, PasswordEncoder PasswordEncoder
    , JwtService JwtService, AuthenticationManager AuthenticationManager){
        userRepository = UserRepository;
        passwordEncoder = PasswordEncoder;
        jwtService = JwtService;
        authenticationManager = AuthenticationManager;
    }

    public User save(User theUser){
        return userRepository.save(theUser);
    }

    public User update(User theUser){
        return userRepository.save(theUser);
    }

    public User findUserById(String email) throws Exception{
        Optional<User> result = userRepository.findById(email);
        User theUser = null;
        if(result.isPresent()){
            theUser = result.get();
        }
        else{
            throw new EntityNotFoundException(" did not find user with email " + email);
        }
        return theUser;
    }

    public AuthenticationResponse register(RegisterRequest request) throws Exception{
        var theuser = userRepository.findByEmail(request.getEmail());
        if (theuser.isEmpty()) {
            var user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .mobile(request.getLocation())
                    .role(Role.User)
                    .build();

            User theUser = userRepository.save(user);

            var jwtToken = jwtService.generateToken(theUser);
            return AuthenticationResponse.builder()
                    .user(theUser)
                    .token(jwtToken)
                    .build();
        }
        else{
            throw new EntityNotFoundException("Username already exists");
        }
    }

    public AuthenticationResponse authenticate(LoginRequest request) throws Exception{
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Incorrect Username or Password");
        }

        var theUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow( () -> new EntityNotFoundException("username not found"+ request.getEmail()) );
        var jwtToken = jwtService.generateToken(theUser);
        return AuthenticationResponse.builder()
                .user(theUser)
                .token(jwtToken)
                .build();

    }
}
