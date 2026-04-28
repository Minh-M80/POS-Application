package com.example.minhm80.service.impl;

import com.example.minhm80.configuration.JwtProvider;
import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.modal.User;
import com.example.minhm80.repository.UserRepository;
import com.example.minhm80.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;



    @Override
    public User getUserFromJwtToken(String token) throws UserException {
        String email = jwtProvider.getEmailFromToken(token);
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UserException("Invalid token", HttpStatus.UNAUTHORIZED);
        }
        return user;
    }

    @Override
    public User getCurrentUser() throws UserException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("authentication: {}", authentication);
        if (authentication == null || authentication.getName() == null) {
            throw new UserException("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UserException("user not found", HttpStatus.NOT_FOUND);
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UserException("user not found", HttpStatus.NOT_FOUND);
        }

        return user;
    }

    @Override
    public User getUserById(Long id) throws UserException {
        return userRepository.findById(id).orElseThrow(
                ()->new UserException("User not found", HttpStatus.NOT_FOUND)
        );

    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}
