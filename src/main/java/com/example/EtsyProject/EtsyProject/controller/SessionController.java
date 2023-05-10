package com.example.EtsyProject.EtsyProject.controller;

import com.example.EtsyProject.EtsyProject.dao.UserRepository;
import com.example.EtsyProject.EtsyProject.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/session")
public class SessionController {
    private UserRepository userRepository;

    @Autowired
    public SessionController(UserRepository UserRepository){
        userRepository = UserRepository;
    }
    @GetMapping("/hello")
    public ResponseEntity<Optional<User>> UserSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        return ResponseEntity.ok(userRepository.findByEmail(name));
    }
}
