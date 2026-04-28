package com.example.minhm80.payload.request;

import com.example.minhm80.domain.UserRole;
import lombok.Data;

@Data
public class SignupRequest {
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private UserRole role;
}
