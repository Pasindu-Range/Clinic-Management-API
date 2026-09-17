package com.example.clinic2.dto.doctorDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorCreateDto {
    @NotNull(message = "Name should be added.")
    private String name;
    @NotNull(message = "Specialization should be added.")
    private String specialization;
}
