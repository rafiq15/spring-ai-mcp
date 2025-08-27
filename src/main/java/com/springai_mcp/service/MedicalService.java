package com.springai_mcp.service;

import com.springai_mcp.dto.MedicalReportDTO;
import com.springai_mcp.dto.PatientDTO;
import com.springai_mcp.entity.MedicalReport;
import com.springai_mcp.entity.Patient;
import com.springai_mcp.repository.MedicalReportRepository;
import com.springai_mcp.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MedicalService {
    private final PatientRepository patientRepository;
    private final MedicalReportRepository reportRepository;

    public MedicalService(PatientRepository patientRepository, MedicalReportRepository reportRepository) {
        this.patientRepository = patientRepository;
        this.reportRepository = reportRepository;
    }

    // Initialize sample data
    @PostConstruct
    public void init() {
        Patient patient1 = new Patient();
        patient1.setName("John Doe");
        patient1.setDateOfBirth(LocalDate.of(1980, 5, 15));
        patient1.setGender("Male");
        patientRepository.save(patient1);

        MedicalReport report1 = new MedicalReport();
        report1.setPatient(patient1);
        report1.setReportDate(LocalDate.now());
        report1.setDiagnosis("Flu");
        report1.setContent("Patient shows symptoms of seasonal flu. Prescribed rest and medication.");
        reportRepository.save(report1);

        Patient patient2 = new Patient();
        patient2.setName("Jane Smith");
        patient2.setDateOfBirth(LocalDate.of(1990, 8, 22));
        patient2.setGender("Female");
        patientRepository.save(patient2);

        MedicalReport report2 = new MedicalReport();
        report2.setPatient(patient2);
        report2.setReportDate(LocalDate.now().minusDays(10));
        report2.setDiagnosis("Checkup");
        report2.setContent("Routine checkup. All vitals normal.");
        reportRepository.save(report2);
    }

    @Tool(name = "list_patients", description = "Get a list of all patients")
    public List<PatientDTO> listPatients() {
        return patientRepository.findAll().stream()
                .map(this::toPatientDTO)
                .collect(Collectors.toList());
    }

    @Tool(name = "get_patient_info", description = "Get information about a patient by name")
    public Optional<PatientDTO> getPatientInfo(String name) {
        return patientRepository.findByName(name).map(this::toPatientDTO);
    }

    @Tool(name = "list_reports_for_patient", description = "Get all medical reports for a patient by patient ID")
    public List<MedicalReportDTO> listReportsForPatient(Long patientId) {
        return reportRepository.findByPatientId(patientId).stream()
                .map(this::toMedicalReportDTO)
                .collect(Collectors.toList());
    }

    @Tool(name = "add_medical_report", description = "Add a new medical report for a patient by patient ID, diagnosis, and content")
    public MedicalReportDTO addMedicalReport(Long patientId, String diagnosis, String content) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        MedicalReport report = new MedicalReport();
        report.setPatient(patient);
        report.setReportDate(LocalDate.now());
        report.setDiagnosis(diagnosis);
        report.setContent(content);
        MedicalReport savedReport = reportRepository.save(report);
        return toMedicalReportDTO(savedReport);
    }

    @Tool(name = "update_medical_report", description = "Update an existing medical report by report ID, new diagnosis, and new content")
    public MedicalReportDTO updateMedicalReport(Long reportId, String diagnosis, String content) {
        MedicalReport report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        report.setDiagnosis(diagnosis);
        report.setContent(content);
        MedicalReport updatedReport = reportRepository.save(report);
        return toMedicalReportDTO(updatedReport);
    }

    // Custom exception for not found resources
    public static class NotFoundException extends RuntimeException {
        public NotFoundException(String message) {
            super(message);
        }
    }

    private PatientDTO toPatientDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setName(patient.getName());
        dto.setDateOfBirth(patient.getDateOfBirth());
        dto.setGender(patient.getGender());
        return dto;
    }

    private MedicalReportDTO toMedicalReportDTO(MedicalReport report) {
        MedicalReportDTO dto = new MedicalReportDTO();
        dto.setId(report.getId());
        dto.setPatientId(report.getPatient().getId());
        dto.setReportDate(report.getReportDate());
        dto.setDiagnosis(report.getDiagnosis());
        dto.setContent(report.getContent());
        return dto;
    }
}