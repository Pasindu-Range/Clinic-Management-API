package com.example.clinic2.controller;

import com.example.clinic2.dto.doctorDto.DoctorCreateDto;
import com.example.clinic2.dto.doctorDto.DoctorDetailsDto;
import com.example.clinic2.dto.doctorDto.DoctorResponseDto;
import com.example.clinic2.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private DoctorService doctorService;

    public  DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping()
    public List<DoctorResponseDto> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorResponseDto getDoctorById(@PathVariable Long id){
        return doctorService.getDoctorById(id);
    }

    @PostMapping()
    public DoctorResponseDto createDoctor(@Valid @RequestBody DoctorCreateDto dto){
        return doctorService.createDoctor(dto);
    }

    @PutMapping("/{id}")
    public DoctorResponseDto updateDoctor(@PathVariable Long id, @Valid @RequestBody DoctorCreateDto dto){
        return doctorService.updateDoctor(id, dto);
    }

    @DeleteMapping("{id}")
    public String deleteDoctor(@PathVariable Long id){
        return doctorService.deleteDoctor(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<DoctorResponseDto>> searchDoctors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialization,
            @PageableDefault(size = 10, sort = "name")
            Pageable pageable) {

        return ResponseEntity.ok(
                doctorService.searchDoctors(
                        name,
                        specialization,
                        pageable
                )
        );
    }

    @GetMapping("/{id}/patients")
    public ResponseEntity<DoctorDetailsDto> getDoctorDetails(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                doctorService.getDoctorDetails(id)
        );
    }
}
