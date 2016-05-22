package com.example.springboot.ad.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UIAuthenticationRequest {
    private String username;
    private String password;
}
