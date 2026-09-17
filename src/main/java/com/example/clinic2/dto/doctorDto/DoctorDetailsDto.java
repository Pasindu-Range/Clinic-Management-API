package com.example.clinic2.dto.doctorDto;

import com.example.clinic2.dto.patientDto.PatientResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DoctorDetailsDto {

    private Long id;
    private String name;
    private String specialization;
    private List<PatientResponseDto> patients;

    public DoctorDetailsDto(
            Long id,
            String name,
            String specialization,
            List<PatientResponseDto> patients) {

        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.patients = patients;
    }


}
