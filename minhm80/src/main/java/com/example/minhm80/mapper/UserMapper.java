package com.example.minhm80.mapper;

import com.example.minhm80.modal.User;
import com.example.minhm80.payload.dto.UserDto;

public class UserMapper {

    public static UserDto toDTO(User savedUser) {
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setFullName(savedUser.getFullName());
        userDto.setEmail(savedUser.getEmail());
        userDto.setRole(savedUser.getRole());
        userDto.setCreateAt(savedUser.getCreateAt());
        userDto.setLastLogin(savedUser.getLastLogin());
        userDto.setUpdateAt(savedUser.getUpdateAt());

        userDto.setPhone(savedUser.getPhone());
        return  userDto;
    }
}
