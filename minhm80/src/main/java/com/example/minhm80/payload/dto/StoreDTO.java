package com.example.minhm80.payload.dto;

import com.example.minhm80.domain.StoreStatus;
import com.example.minhm80.modal.StoreContact;
import com.example.minhm80.modal.User;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreDTO {
    private Long id;


    private String brand;


    private UserDto storeAdmin;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    private String description;

    private String storeType;

    private StoreStatus status;


    private StoreContact contact;
}