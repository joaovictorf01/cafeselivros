package com.cafeselivros.api.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String identifier; // username ou email
    private String password;
}
