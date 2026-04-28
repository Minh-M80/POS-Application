package com.example.minhm80.payload.request;

import com.example.minhm80.domain.OrderStatus;
import com.example.minhm80.domain.PaymentType;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private Long customerId;
    private PaymentType paymentType;
    private OrderStatus status;
    private List<OrderItemRequest> items;
}
