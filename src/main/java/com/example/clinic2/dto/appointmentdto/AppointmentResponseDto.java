package com.example.clinic2.dto.appointmentdto;

import com.example.clinic2.entity.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDto {
    private Long id;
    private String doctorName;
    private String patientName;
    private LocalDate date;
    private String reason;
    private AppointmentStatus appointmentStatus;
}
