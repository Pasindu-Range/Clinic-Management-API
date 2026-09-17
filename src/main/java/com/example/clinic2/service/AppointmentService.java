package com.example.clinic2.service;

import com.example.clinic2.dto.appointmentdto.AppointmentCreateDto;
import com.example.clinic2.dto.appointmentdto.AppointmentResponseDto;
import com.example.clinic2.dto.appointmentdto.AppointmentUpdateDto;
import com.example.clinic2.entity.Appointment;
import com.example.clinic2.entity.AppointmentStatus;
import com.example.clinic2.entity.Doctor;
import com.example.clinic2.entity.Patient;
import com.example.clinic2.exception.AppointmentNotFoundException;
import com.example.clinic2.exception.DoctorNotFoundException;
import com.example.clinic2.mapper.AppointmentMapper;
import com.example.clinic2.repo.AppointmentRepository;
import com.example.clinic2.repo.DoctorRepository;
import com.example.clinic2.repo.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public Page<AppointmentResponseDto> getAllAppointments(
            Pageable pageable) {

        Page<Appointment> appointments =
                appointmentRepository.findAll(pageable);

        return appointments.map(AppointmentMapper::toDto);
    }

    public AppointmentResponseDto getAppointmentById(Long id){
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment not found with id: " + id
                        )
                );

        return AppointmentMapper.toDto(appointment);
    }

    public AppointmentResponseDto createAppointment(AppointmentCreateDto dto){

        Doctor existedDoctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow(() -> new RuntimeException("Doctor Not Found."));
        Patient existedPatient = patientRepository.findById(dto.getPatientId()).orElseThrow(() -> new RuntimeException("Patient Not Found."));

        Appointment newAppointment = AppointmentMapper.toEntity(existedPatient,existedDoctor,dto);

        Appointment updatedAppointment = appointmentRepository.save(newAppointment);
        return AppointmentMapper.toDto(updatedAppointment);
    }

    public void rescheduleAppointment(Long appointmentId, AppointmentUpdateDto dto) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment not found with id: " + appointmentId
                        )
                );

        AppointmentMapper.rescheduleAppointment(appointment, dto);

        appointmentRepository.save(appointment);
    }

    public void updateAppointmentStatus(
            Long appointmentId,
            AppointmentStatus status) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                new AppointmentNotFoundException(
                        "Appointment not found with id: " + appointmentId
                )
        );

        appointment.setAppointmentStatus(status);

        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() ->
                        new AppointmentNotFoundException(
                                "Appointment not found with id: " + appointmentId
                        )
                );

        appointmentRepository.delete(appointment);
    }




}
