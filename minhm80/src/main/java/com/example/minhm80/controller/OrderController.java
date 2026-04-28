package com.example.minhm80.controller;

import com.example.minhm80.domain.OrderStatus;
import com.example.minhm80.domain.PaymentType;
import com.example.minhm80.payload.dto.OrderDTO;
import com.example.minhm80.payload.dto.OrderItemDTO;
import com.example.minhm80.payload.request.CreateOrderRequest;
import com.example.minhm80.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(
            @RequestBody CreateOrderRequest request) throws Exception {

        List<OrderItemDTO> itemDTOs = request.getItems().stream().map(item -> {
            OrderItemDTO dto = OrderItemDTO.builder()
                    .productId(item.getProductId())
                    .quantity(item.getQuantity())
                    .build();
            return dto;
        }).collect(Collectors.toList());

        OrderDTO orderDTO = OrderDTO.builder()
                .customerId(request.getCustomerId())
                .paymentType(request.getPaymentType())
                .status(request.getStatus())
                .items(itemDTOs)
                .build();

        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(
            @PathVariable Long id
    ) throws Exception {
            return ResponseEntity.ok(orderService.getOrderById(id));
    }


    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDTO>> getOrderByBranch(
            @PathVariable Long branchId,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long cashierId,
            @RequestParam(required = false) PaymentType paymentType,
            @RequestParam(required = false) OrderStatus status


    ) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByBranch(branchId, customerId, cashierId, paymentType, status));
    }

    @GetMapping("/cashier/{id}")
    public ResponseEntity<List<OrderDTO>> getOrderByCashier(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(orderService.getOrderByCashier(id));
    }


    @GetMapping("/today/branch/{id}")
    public ResponseEntity<List<OrderDTO>> getTodayOrder(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(orderService.getTodayOrdersByBranch(id));
    }


    @GetMapping("/customer/{id}")
    public ResponseEntity<List<OrderDTO>> getCustomersOrder(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(id));
    }

    @GetMapping("/recent/{branchId}")
    public ResponseEntity<List<OrderDTO>> getRecentOrder(
            @PathVariable Long branchId
    ) throws Exception {
        return ResponseEntity.ok(orderService.getTop5RecentOrderByBranchId(branchId));
    }

}
