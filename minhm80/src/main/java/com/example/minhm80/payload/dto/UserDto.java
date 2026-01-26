package com.example.minhm80.payload.dto;

import com.example.minhm80.domain.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UserDto {

    private Long id;

    private String fullName;

    private String email;

    private String phone;




    private UserRole role;


    private String password;



    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime lastLogin;

}
