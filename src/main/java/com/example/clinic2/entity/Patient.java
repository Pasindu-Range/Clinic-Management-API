package com.example.clinic2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    private int phone;

    @ManyToOne
    @JoinColumn(name= "doctor_id")
    private Doctor doctor;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;


}
