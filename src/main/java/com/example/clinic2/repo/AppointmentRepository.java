package com.example.clinic2.repo;

import com.example.clinic2.entity.Appointment;
import com.example.clinic2.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("""
        SELECT DISTINCT a.patient
        FROM Appointment a
        WHERE a.doctor.id = :doctorId
        """)
    List<Patient> findPatientsByDoctorId(@Param("doctorId") Long doctorId);
}
