package com.example.minhm80.payload.request;

import lombok.Data;

@Data
public class CreateCustomerRequest {
    private String fullName;
    private String email;
    private String phone;
}
