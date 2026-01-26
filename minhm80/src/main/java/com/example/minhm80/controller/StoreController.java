package com.example.minhm80.controller;

import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.StoreDTO;
import com.example.minhm80.service.StoreService;
import com.example.minhm80.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {
    private final StoreService storeService;
    private final UserService userService;

    public ResponseEntity<StoreDTO> createStore(@RequestBody StoreDTO storeDTO,
                                                    @RequestHeader("Authorization") String jwt
    ) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);

    }


















}
