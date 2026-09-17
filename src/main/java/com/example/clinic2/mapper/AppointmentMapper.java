package com.example.clinic2.mapper;

import com.example.clinic2.dto.appointmentdto.AppointmentCreateDto;
import com.example.clinic2.dto.appointmentdto.AppointmentResponseDto;
import com.example.clinic2.dto.appointmentdto.AppointmentUpdateDto;
import com.example.clinic2.entity.Appointment;
import com.example.clinic2.entity.AppointmentStatus;
import com.example.clinic2.entity.Doctor;
import com.example.clinic2.entity.Patient;

import java.util.List;

public class AppointmentMapper {

    public static AppointmentResponseDto toDto(Appointment appointment){
        return new AppointmentResponseDto(appointment.getId(),appointment.getDoctor().getName(),appointment.getPatient().getName(),appointment.getAppointmentDate(), appointment.getReason(),appointment.getAppointmentStatus());
    }
    public static List<AppointmentResponseDto> toDtoList(List<Appointment> appointments){
        return appointments.stream()
                .map(AppointmentMapper::toDto).toList();
    }

    public static Appointment toEntity(Patient patient, Doctor doctor, AppointmentCreateDto dto){
        Appointment ap = new Appointment();
        ap.setDoctor(doctor);
        ap.setPatient(patient);
        ap.setAppointmentDate(dto.getDate());
        ap.setReason(dto.getReason());

        ap.setAppointmentStatus(AppointmentStatus.BOOKED);

        return ap;
    }

    public static void rescheduleAppointment(
            Appointment appointment,
            AppointmentUpdateDto dto)
    {

        appointment.setAppointmentDate(dto.getDate());

    }
}
