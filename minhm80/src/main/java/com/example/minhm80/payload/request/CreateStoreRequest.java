package com.example.minhm80.payload.request;

import com.example.minhm80.modal.StoreContact;
import lombok.Data;

@Data
public class CreateStoreRequest {
    private String brand;
    private String description;
    private String storeType;
    private StoreContact contact;
}
