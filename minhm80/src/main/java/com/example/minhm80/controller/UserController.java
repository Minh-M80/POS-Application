package com.example.minhm80.controller;


import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.mapper.UserMapper;
import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.UserDto;
import com.example.minhm80.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;



    @GetMapping("profile")
    public ResponseEntity<UserDto> getUserProfile() throws UserException {
        User user = userService.getCurrentUser();
        log.info("user:{}",user.getLastLogin());
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @PathVariable Long id
    ) throws UserException {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUser(

    ) throws UserException {

        return ResponseEntity.ok(userService.getAllUsers().stream().map(
                UserMapper::toDTO
        ).toList());
    }




}
