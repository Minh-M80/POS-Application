package com.example.minhm80.mapper;

import com.example.minhm80.modal.Order;
import com.example.minhm80.payload.dto.OrderDTO;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDTO(Order order){
        return OrderDTO.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .branchId(order.getBranch().getId())
                .cashier(UserMapper.toDTO(order.getCashier()))
                .paymentType(order.getPaymentType())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .items(order.getItems().stream().map(
                        OrderItemMapper::toDTO
                ).collect(Collectors.toList()))
                .build();
    }
}
