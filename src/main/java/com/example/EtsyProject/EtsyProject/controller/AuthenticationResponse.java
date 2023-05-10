package com.example.EtsyProject.EtsyProject.controller;

import com.example.EtsyProject.EtsyProject.entity.User;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;
    private User user;
}
