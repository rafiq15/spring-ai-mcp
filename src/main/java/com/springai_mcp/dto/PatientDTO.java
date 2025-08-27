package com.springai_mcp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDTO {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
}