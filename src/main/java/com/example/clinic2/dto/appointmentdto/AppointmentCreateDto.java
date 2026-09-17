package com.example.clinic2.dto.appointmentdto;

import com.example.clinic2.entity.AppointmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreateDto {
    @NotNull(message ="Can't be empty")
    private Long doctorId;
    @NotNull(message = "Can't be empty.")
    private Long patientId;
    @NotNull(message = "Date should added.")
    private LocalDate date;
    private String reason;

    private AppointmentStatus appointmentStatus;

}
