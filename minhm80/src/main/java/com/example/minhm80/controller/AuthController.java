package com.example.minhm80.controller;

import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.payload.dto.UserDto;
import com.example.minhm80.payload.request.LoginRequest;
import com.example.minhm80.payload.request.SignupRequest;
import com.example.minhm80.payload.response.AuthResponse;
import com.example.minhm80.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

 //  localhost:5000/auth/signup

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(
            @RequestBody SignupRequest request
            ) throws UserException {
        UserDto userDto = new UserDto();
        userDto.setFullName(request.getFullName());
        userDto.setEmail(request.getEmail());
        userDto.setPhone(request.getPhone());
        userDto.setPassword(request.getPassword());
        userDto.setRole(request.getRole());

        return ResponseEntity.ok(
                authService.signup(userDto)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(
            @RequestBody LoginRequest request
    ) throws UserException {
        UserDto userDto = new UserDto();
        userDto.setEmail(request.getEmail());
        userDto.setPassword(request.getPassword());

        return ResponseEntity.ok(
                authService.login(userDto)
        );
    }

}
