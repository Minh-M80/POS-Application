package com.example.minhm80.payload.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
