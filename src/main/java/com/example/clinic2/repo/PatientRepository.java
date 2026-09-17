package com.example.clinic2.repo;

import com.example.clinic2.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
