package com.example.clinic2.mapper;

import com.example.clinic2.dto.patientDto.PatientCreateDto;
import com.example.clinic2.dto.patientDto.PatientResponseDto;
import com.example.clinic2.entity.Patient;

import java.util.List;

public class PatientMapper {

    public static PatientResponseDto toDto(Patient patient){
        return new PatientResponseDto(patient.getName(), patient.getAge(), patient.getPhone());
    }

    public static List<PatientResponseDto> toDtoList(List<Patient> patients){
        return patients.stream()
                .map(PatientMapper::toDto).toList();
    }

    public static Patient toEntity(PatientCreateDto dto){
        Patient p1 = new Patient();
        p1.setName(dto.getName());
        p1.setAge(dto.getAge());
        p1.setPhone(dto.getPhone());

        return p1;
    }

    public static void updateEntity(Patient patient, PatientCreateDto dto){
        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setPhone(dto.getPhone());
    }
}
