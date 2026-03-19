package com.example.minhm80.service;

import com.example.minhm80.exceptions.UserException;
import com.example.minhm80.modal.ShiftReport;
import com.example.minhm80.payload.dto.ShiftReportDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ShiftReportService  {

    ShiftReportDTO startShift(Long cashierId,
                           Long branchId,
                           LocalDateTime shiftStart) throws Exception;

    ShiftReportDTO endShift(Long shiftReportId,
                         LocalDateTime shiftEnd) throws Exception;

    ShiftReportDTO getShiftReportById(Long id);

    List<ShiftReportDTO> getAllShiftReports();

    List<ShiftReportDTO> getShiftReportsByBranchId(Long branchId);


    List<ShiftReportDTO> getShiftReportsByCashierId(Long cashierId);


    ShiftReportDTO getCurrentShiftProgress(Long cashierId) throws Exception;

    ShiftReportDTO getShiftByCashierAndDate(Long cashierId,LocalDateTime date) throws Exception;




}
