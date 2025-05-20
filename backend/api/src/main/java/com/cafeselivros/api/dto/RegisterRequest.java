package com.cafeselivros.api.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String username;
    private String password;
}
