package com.example.clinic2.repo;

import com.example.clinic2.entity.Doctor;
import com.example.clinic2.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

    Page<Doctor> findBySpecializationContainingIgnoreCase(
            String specialization,
            Pageable pageable
    );


}
