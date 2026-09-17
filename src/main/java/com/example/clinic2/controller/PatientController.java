package com.example.clinic2.controller;

import com.example.clinic2.dto.patientDto.PatientCreateDto;
import com.example.clinic2.dto.patientDto.PatientResponseDto;
import com.example.clinic2.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    private PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public List<PatientResponseDto> getPatients() {
        return patientService.getAll();
    }

    @GetMapping("/{id}")
    public PatientResponseDto getPatientById(@PathVariable Long id){
        return patientService.getById(id);
    }

    @PostMapping()
    public PatientResponseDto addPatient(@Valid @RequestBody PatientCreateDto dto){
        return patientService.createPatient(dto);
    }

    @PutMapping("/{id}")
    public PatientResponseDto updatePatient(@PathVariable Long id,@Valid @RequestBody PatientCreateDto dto){
        return patientService.updatePatient(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id){
        return patientService.deletePatient(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PatientResponseDto>> searchPatients(
            @RequestParam String name,
            @PageableDefault(size = 10, sort = "name")
            Pageable pageable) {

        return ResponseEntity.ok(
                patientService.searchPatients(name, pageable)
        );
    }
}
