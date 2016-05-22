package com.example.springboot.ad.model.security;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class AuthToken {
    private String tokenValue;
    private LocalDateTime timeCreated;
    private UserDetails user;
}
