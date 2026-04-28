package com.example.minhm80.payload.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UpdateBranchRequest {
    private String name;
    private String address;
    private String phone;
    private String email;
    private List<String> workingDays;
    private LocalDateTime openTime;
    private LocalDateTime closeTime;
    private Long storeId;
}
