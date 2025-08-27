package com.springai_mcp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicalReportDTO {
    private Long id;
    private Long patientId; // Include patient ID instead of full Patient object
    private LocalDate reportDate;
    private String diagnosis;
    private String content;
}