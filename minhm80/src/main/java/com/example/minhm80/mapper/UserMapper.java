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
        userDto.setUpdateAt(savedUser.getUpdatedAt());

        userDto.setPhone(savedUser.getPhone());
        userDto.setStoreId(savedUser.getStore() != null ?  savedUser.getStore().getId():null);
        userDto.setBranchId( savedUser.getBranch()!=null ? savedUser.getBranch().getId():null);
        return  userDto;
    }

    public static User toEntity(UserDto userDto){
        User createdUser = new User();

        createdUser.setEmail(userDto.getEmail());
        createdUser.setFullName(userDto.getFullName());
        createdUser.setRole(userDto.getRole());
        createdUser.setCreateAt(userDto.getCreateAt());
        createdUser.setUpdatedAt(userDto.getUpdateAt());
        createdUser.setLastLogin(userDto.getLastLogin());
        createdUser.setPhone(userDto.getPhone());
        createdUser.setPassword(userDto.getPassword());

        return createdUser;


    }
}
