package com.eliteperformance.model;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class Employee {
    private Long employeeId;
    private String name;
    private String designation;
    private BigDecimal baseSalary;
    private String role;
    private LocalDate lastPromotionDate;
    private String username;
    private String password;
}