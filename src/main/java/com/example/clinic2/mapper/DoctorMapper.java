package com.example.clinic2.mapper;

import com.example.clinic2.dto.doctorDto.DoctorCreateDto;
import com.example.clinic2.dto.doctorDto.DoctorDetailsDto;
import com.example.clinic2.dto.doctorDto.DoctorResponseDto;
import com.example.clinic2.dto.patientDto.PatientResponseDto;
import com.example.clinic2.entity.Doctor;

import java.util.List;

public class DoctorMapper {

    public static DoctorResponseDto toDto(Doctor doctor) {
        return new DoctorResponseDto(doctor.getName(), doctor.getSpecialization());
    }

    public static List<DoctorResponseDto> toDtoList(List<Doctor> doctors){
        return doctors.stream()
                .map(DoctorMapper::toDto).toList();
    }

    public static Doctor toEntity(DoctorCreateDto dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());

        return doctor;
    }

    public static void updateEntity(Doctor doctor,DoctorCreateDto dto) {
        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
    }

    public static DoctorDetailsDto toDetailsDto(
            Doctor doctor,
            List<PatientResponseDto> patients) {

        return new DoctorDetailsDto(
                doctor.getId(),
                doctor.getName(),
                doctor.getSpecialization(),
                patients
        );
    }
}
