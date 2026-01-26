package com.example.minhm80.service;

import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.payload.dto.UserDto;
import com.example.minhm80.payload.response.AuthResponse;

public interface AuthService {
    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto) throws UserException;

}
