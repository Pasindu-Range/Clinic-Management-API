package com.example.clinic2.service;

import com.example.clinic2.dto.doctorDto.DoctorCreateDto;
import com.example.clinic2.dto.doctorDto.DoctorDetailsDto;
import com.example.clinic2.dto.doctorDto.DoctorResponseDto;
import com.example.clinic2.dto.patientDto.PatientResponseDto;
import com.example.clinic2.entity.Doctor;
import com.example.clinic2.entity.Patient;
import com.example.clinic2.exception.DoctorNotFoundException;
import com.example.clinic2.exception.GlobalExceptionHandler;
import com.example.clinic2.mapper.DoctorMapper;
import com.example.clinic2.mapper.PatientMapper;
import com.example.clinic2.repo.AppointmentRepository;
import com.example.clinic2.repo.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<DoctorResponseDto> getAllDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        return DoctorMapper.toDtoList(doctors);
    }

    public DoctorResponseDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with id: " + id
                        )
                );

        return DoctorMapper.toDto(doctor);
    }

    public DoctorResponseDto createDoctor(DoctorCreateDto dto) {
        Doctor doctor= DoctorMapper.toEntity(dto);
        Doctor savedDoctor = doctorRepository.save(doctor);

        return DoctorMapper.toDto(savedDoctor);
    }

    public DoctorResponseDto updateDoctor(Long id, DoctorCreateDto dto) {
        Doctor existedDoctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with id: " + id
                        )
                );
        DoctorMapper.updateEntity(existedDoctor,dto);

        Doctor updatedDoctor = doctorRepository.save(existedDoctor);
        return DoctorMapper.toDto(updatedDoctor);
    }

    public String deleteDoctor(Long id) {
        if(doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return "Doctor Deleted.";
        }
        throw new DoctorNotFoundException(
                        "Doctor not found with id: " + id
        );
    }

    public Page<DoctorResponseDto> searchDoctors(
            String name,
            String specialization,
            Pageable pageable) {

        Page<Doctor> doctors;

        if (name != null && !name.isBlank()) {

            doctors = doctorRepository
                    .findByNameContainingIgnoreCase(name, pageable);

        } else if (specialization != null && !specialization.isBlank()) {

            doctors = doctorRepository
                    .findBySpecializationContainingIgnoreCase(
                            specialization,
                            pageable
                    );

        } else {

            doctors = doctorRepository.findAll(pageable);
        }

        return doctors.map(DoctorMapper::toDto);
    }

    public DoctorDetailsDto getDoctorDetails(Long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new DoctorNotFoundException(
                                "Doctor not found with id: " + doctorId
                        )
                );

        List<Patient> patients =
                appointmentRepository.findPatientsByDoctorId(doctorId);

        List<PatientResponseDto> patientDtos =
                PatientMapper.toDtoList(patients);

        return DoctorMapper.toDetailsDto(
                doctor,
                patientDtos
        );
    }

}
