package com.example.minhm80.payload.dto;

import com.example.minhm80.modal.Store;
import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class CategoryDTO {


    private Long id;

    private String name;



//    private Store store;


    private Long storeId;
}
