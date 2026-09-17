package com.example.clinic2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate appointmentDate;
    private String reason;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentStatus;

    @ManyToOne
    @JoinColumn(name= "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name= "doctor_id")
    private Doctor doctor;
}
