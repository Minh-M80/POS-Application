package com.example.minhm80.service;

import com.example.minhm80.modal.Refund;
import com.example.minhm80.payload.dto.RefundDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundService {

    RefundDTO createRefund(RefundDTO refund) throws Exception;
    List<RefundDTO> getAllRefunds() throws Exception;

    List<RefundDTO> getRefundByCashier(Long cashierId) throws Exception;

    List<RefundDTO> getRefundByShiftReport(Long shiftReportId) throws  Exception;

    List<RefundDTO> getRefundByCashierAndDateRange(Long cashierId,
                                                   LocalDateTime startDate,
                                                   LocalDateTime endDate) throws Exception;

    List<RefundDTO> getRefundByBranch(Long branchId) throws Exception;

    RefundDTO getRefundById(Long refundId) throws Exception;

    void deleteRefund(Long refundId) throws Exception;


}
