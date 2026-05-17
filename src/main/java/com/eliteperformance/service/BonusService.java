package com.eliteperformance.service;

import com.eliteperformance.dto.KpiRequestDTO;
import com.eliteperformance.dto.BonusResponseDTO;
import com.eliteperformance.exception.DuplicateReviewException;
import com.eliteperformance.exception.ResourceNotFoundException;
import com.eliteperformance.model.BonusRecord;
import com.eliteperformance.model.Employee;
import com.eliteperformance.model.PerformanceReview;
import com.eliteperformance.repository.BonusRecordRepository;
import com.eliteperformance.repository.EmployeeRepository;
import com.eliteperformance.repository.PerformanceReviewRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class BonusService {

    private final EmployeeRepository employeeRepository;
    private final PerformanceReviewRepository performanceReviewRepository;
    private final BonusRecordRepository bonusRecordRepository;

    public BonusService(EmployeeRepository employeeRepository,
                        PerformanceReviewRepository performanceReviewRepository,
                        BonusRecordRepository bonusRecordRepository) {
        this.employeeRepository = employeeRepository;
        this.performanceReviewRepository = performanceReviewRepository;
        this.bonusRecordRepository = bonusRecordRepository;
    }

    @Transactional
    public BonusResponseDTO calculateBonus(KpiRequestDTO request) {

        // Check employee exists
        Employee employee = employeeRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + request.getEmployeeId()));

        // Check duplicate review
        if (performanceReviewRepository.existsByEmployeeIdAndYear(request.getEmployeeId(), request.getReviewYear())) {
            throw new DuplicateReviewException("Review already exists for employee " + request.getEmployeeId() + " for year " + request.getReviewYear());
        }

        // Calculate total KPI score
        double totalKpi = request.getTaskCompletion() + request.getAttendance() +
                request.getTeamCollaboration() + request.getProblemSolving() +
                request.getCommunication() + request.getLeadership() +
                request.getClientSatisfaction();

        // Determine category and bonus percentage
        String category;
        double bonusPercentage;

        if (totalKpi >= 90) {
            category = "Gold Tier";
            bonusPercentage = 20.0;
        } else if (totalKpi >= 75) {
            category = "Silver Tier";
            bonusPercentage = 12.0;
        } else if (totalKpi >= 60) {
            category = "Bronze Tier";
            bonusPercentage = 5.0;
        } else {
            category = "No Tier";
            bonusPercentage = 0.0;
        }

        // Calculate bonus amount
        BigDecimal baseSalary = employee.getBaseSalary();
        BigDecimal bonusAmount = baseSalary
                .multiply(BigDecimal.valueOf(bonusPercentage))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal totalCompensation = baseSalary.add(bonusAmount);

        // Save performance review
        PerformanceReview review = new PerformanceReview();
        review.setEmployeeId(request.getEmployeeId());
        review.setReviewYear(request.getReviewYear());
        review.setTaskCompletion(BigDecimal.valueOf(request.getTaskCompletion()));
        review.setAttendance(BigDecimal.valueOf(request.getAttendance()));
        review.setTeamCollaboration(BigDecimal.valueOf(request.getTeamCollaboration()));
        review.setProblemSolving(BigDecimal.valueOf(request.getProblemSolving()));
        review.setCommunication(BigDecimal.valueOf(request.getCommunication()));
        review.setLeadership(BigDecimal.valueOf(request.getLeadership()));
        review.setClientSatisfaction(BigDecimal.valueOf(request.getClientSatisfaction()));
        review.setTotalKpiScore(BigDecimal.valueOf(totalKpi));
        performanceReviewRepository.save(review);

        // Save bonus record
        BonusRecord bonusRecord = new BonusRecord();
        bonusRecord.setEmployeeId(request.getEmployeeId());
        bonusRecord.setReviewYear(request.getReviewYear());
        bonusRecord.setTotalKpiScore(BigDecimal.valueOf(totalKpi));
        bonusRecord.setCategory(category);
        bonusRecord.setBonusPercentage(BigDecimal.valueOf(bonusPercentage));
        bonusRecord.setBonusAmount(bonusAmount);
        bonusRecord.setTotalCompensation(totalCompensation);
        employeeRepository.updateLastPromotionDate(request.getEmployeeId());

        // Build response
        BonusResponseDTO response = new BonusResponseDTO();
        response.setEmployeeId(employee.getEmployeeId());
        response.setEmployeeName(employee.getName());
        response.setDesignation(employee.getDesignation());
        response.setReviewYear(request.getReviewYear());
        response.setBaseSalary(baseSalary);
        response.setTotalKpiScore(totalKpi);
        response.setCategory(category);
        response.setBonusPercentage(BigDecimal.valueOf(bonusPercentage));
        response.setBonusAmount(bonusAmount);
        response.setTotalCompensation(totalCompensation);

        return response;
    }
}