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

@Slf4j
@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;



    @GetMapping("profile")
    public ResponseEntity<UserDto> getUserProfile(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        log.info("user:{}",user.getLastLogin());
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long id
    ) throws UserException {
        User user = userService.getUserById(id);
        if (user == null){
            throw new UserException("User not found");
        }
        return ResponseEntity.ok(UserMapper.toDTO(user));
    }




}
