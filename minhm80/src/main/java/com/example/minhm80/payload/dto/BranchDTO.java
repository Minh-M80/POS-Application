package com.example.minhm80.payload.dto;

import com.example.minhm80.modal.Store;
import com.example.minhm80.modal.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BranchDTO {

    private Long id;

    private String name;

    private String address;

    private String phone;

    private String  email;


    private List<String> workingDays;

    private LocalDateTime openTime;

    private LocalDateTime closeTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    private StoreDTO store;

    private Long storeId;


    private UserDto manager;
}
