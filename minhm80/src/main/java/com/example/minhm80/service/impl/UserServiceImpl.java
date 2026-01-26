package com.example.minhm80.service.impl;

import com.example.minhm80.configuration.JwtProvider;
import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.modal.User;
import com.example.minhm80.repository.UserRepository;
import com.example.minhm80.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
            throw new UserException("Invalid token");
        }
        return user;
    }

    @Override
    public User getCurrentUser() throws UserException {
        log.info("authentication: {}",SecurityContextHolder.getContext().getAuthentication());
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UserException("user not found");
        }

        return user;
    }

    @Override
    public User getUserByEmail(String email) throws UserException {
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UserException("user not found");
        }

        return user;
    }

    @Override
    public User getUserById(Long id) throws UserException {
        return userRepository.findById(id).orElseThrow(
                ()->new UserException("User not found")
        );

    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }
}
