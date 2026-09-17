package com.example.clinic2.dto.patientDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDto {
    private String name;
    private int age;
    private int phone;
}
