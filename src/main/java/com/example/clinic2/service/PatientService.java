package com.example.clinic2.service;

import com.example.clinic2.dto.patientDto.PatientCreateDto;
import com.example.clinic2.dto.patientDto.PatientResponseDto;
import com.example.clinic2.entity.Patient;
import com.example.clinic2.exception.PatientNotFoundException;
import com.example.clinic2.mapper.PatientMapper;
import com.example.clinic2.repo.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDto> getAll() {
        List<Patient> patients = patientRepository.findAll();

        return PatientMapper.toDtoList(patients);
    }


    public PatientResponseDto getById(Long id){
        Patient patient= patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundException(
                                "Patient not found with id: " + id
                        )
                );

        return PatientMapper.toDto(patient);
    }

    public PatientResponseDto createPatient(PatientCreateDto dto){
        Patient patient = PatientMapper.toEntity(dto);
        Patient savedPatient = patientRepository.save(patient);

        return PatientMapper.toDto(savedPatient);
    }

    public PatientResponseDto updatePatient(Long id, PatientCreateDto dto){
        Patient existedPatient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundException(
                                "Patient not found with id: " + id
                        )
                );

        PatientMapper.updateEntity(existedPatient, dto);
        Patient updatedPatient = patientRepository.save(existedPatient);
        return PatientMapper.toDto(updatedPatient);
    }

    public String deletePatient(Long id){
        if(patientRepository.existsById(id)){
            patientRepository.deleteById(id);
            return "Patient Deleted.";
        }
        throw new PatientNotFoundException(
                        "Patient not found with id: " + id
        );
    }

    public Page<PatientResponseDto> searchPatients(
            String name,
            Pageable pageable) {

        Page<Patient> patients =
                patientRepository.findByNameContainingIgnoreCase(
                        name,
                        pageable
                );

        return patients.map(PatientMapper::toDto);
    }
}
