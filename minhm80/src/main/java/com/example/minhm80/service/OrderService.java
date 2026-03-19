package com.example.minhm80.service;

import com.example.minhm80.domain.OrderStatus;

import com.example.minhm80.domain.PaymentType;
import com.example.minhm80.payload.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO) throws Exception;
    OrderDTO getOrderById(Long id) throws Exception;

    List<OrderDTO> getOrdersByBranch(Long branchId,
                                     Long customerId,
                                     Long cashierId,
                                     PaymentType paymentType,
                                     OrderStatus status
    ) throws Exception;

    List<OrderDTO> getOrderByCashier(Long cashierId);

    void deleteOrder(Long id) throws Exception;

    List<OrderDTO> getTodayOrdersByBranch(Long branchId) throws Exception;

    List<OrderDTO> getOrdersByCustomerId(Long customerId) throws Exception;

    List<OrderDTO> getTop5RecentOrderByBranchId(Long branchId) throws Exception;








}
