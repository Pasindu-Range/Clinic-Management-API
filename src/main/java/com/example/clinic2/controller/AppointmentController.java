package com.example.clinic2.controller;

import com.example.clinic2.dto.appointmentdto.AppointmentCreateDto;
import com.example.clinic2.dto.appointmentdto.AppointmentResponseDto;
import com.example.clinic2.dto.appointmentdto.AppointmentUpdateDto;
import com.example.clinic2.entity.AppointmentStatus;
import com.example.clinic2.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<Page<AppointmentResponseDto>> getAllAppointments(
            @PageableDefault(size = 10, sort = "appointmentDate")
            Pageable pageable) {

        return ResponseEntity.ok(
                appointmentService.getAllAppointments(pageable)
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                appointmentService.getAppointmentById(id)
        );
    }


    @PostMapping
    public ResponseEntity<AppointmentResponseDto> createAppointment(
            @Valid @RequestBody AppointmentCreateDto dto) {

        AppointmentResponseDto response =
                appointmentService.createAppointment(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @PatchMapping("/{id}/reschedule")
    public ResponseEntity<Void> rescheduleAppointment(
            @PathVariable Long id,
            @Valid @RequestBody AppointmentUpdateDto dto) {

        appointmentService.rescheduleAppointment(id, dto);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateAppointmentStatus(
            @PathVariable Long id,
            @RequestParam AppointmentStatus status) {

        appointmentService.updateAppointmentStatus(id, status);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(
            @PathVariable Long id) {

        appointmentService.deleteAppointment(id);

        return ResponseEntity.noContent().build();
    }
}
