package com.example.EtsyProject.EtsyProject.controller;

import com.example.EtsyProject.EtsyProject.entity.User;
import com.example.EtsyProject.EtsyProject.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserContoller {

    private UserService userService;

    @Autowired
    public UserContoller(UserService theUserService){
        userService = theUserService;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register
            (@RequestBody RegisterRequest request) throws Exception
    {
            return ResponseEntity.ok(userService.register(request));
    }
//    public User saveUser(@RequestBody User theUser){
//        return userService.save(theUser);
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login
            (@RequestBody LoginRequest request) throws Exception
    {
        return ResponseEntity.ok(userService.authenticate(request));
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User theUser){
        return ResponseEntity.ok(userService.update(theUser));
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<Object> findUser(@PathVariable String email) throws Exception{
        User theUser = userService.findUserById(email);
        return new ResponseEntity<Object>(theUser, HttpStatus.OK);
    }
}
