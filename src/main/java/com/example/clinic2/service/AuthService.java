package com.example.clinic2.service;

import com.example.clinic2.dto.auth.LoginRequest;
import com.example.clinic2.dto.auth.RegisterRequest;
import com.example.clinic2.entity.Patient;
import com.example.clinic2.entity.Role;
import com.example.clinic2.entity.User;
import com.example.clinic2.repo.PatientRepository;
import com.example.clinic2.repo.UserRepository;
import com.example.clinic2.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;

    public AuthService(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            PatientRepository patientRepository) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.patientRepository = patientRepository;
    }

    public String login(LoginRequest request) {

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                );

        Authentication authenticated =
                authenticationManager.authenticate(authentication);

        return jwtService.generateToken(authenticated);
    }

    public void register(RegisterRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        Patient patient = new Patient();

        patient.setName(request.getName());
        patient.setAge(request.getAge());
        patient.setPhone(request.getPhone());

        Patient savedPatient = patientRepository.save(patient);

        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.PATIENT);
        user.setPatient(savedPatient);

        userRepository.save(user);
    }
}