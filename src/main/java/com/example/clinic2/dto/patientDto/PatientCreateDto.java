package com.example.clinic2.dto.patientDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientCreateDto {
    @NotNull(message = "Name should be added")
    private String name;
    @NotNull(message = "Age should be added.")
    private int age;
    @NotNull(message = "Phone number should be added.")
    private int phone;
}
