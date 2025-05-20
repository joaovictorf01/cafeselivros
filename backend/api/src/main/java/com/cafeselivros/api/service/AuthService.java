package com.cafeselivros.api.service;

import com.cafeselivros.api.dto.AuthResponse;
import com.cafeselivros.api.dto.LoginRequest;
import com.cafeselivros.api.dto.RegisterRequest;
import com.cafeselivros.api.model.Role;
import com.cafeselivros.api.model.User;
import com.cafeselivros.api.repository.UserRepository;
import com.cafeselivros.api.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }

    public AuthResponse login(LoginRequest request) {
        Optional<User> userOptional = request.getIdentifier().contains("@")
                ? Optional.ofNullable(userRepository.findByEmail(request.getIdentifier()))
                : Optional.ofNullable(userRepository.findByUsername(request.getIdentifier()));

        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
