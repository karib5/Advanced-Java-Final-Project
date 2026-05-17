package com.eliteperformance.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class BonusResponseDTO {
    private Long employeeId;
    private String employeeName;
    private String designation;
    private Integer reviewYear;
    private BigDecimal baseSalary;
    private Double totalKpiScore;
    private String category;
    private BigDecimal bonusPercentage;
    private BigDecimal bonusAmount;
    private BigDecimal totalCompensation;
}