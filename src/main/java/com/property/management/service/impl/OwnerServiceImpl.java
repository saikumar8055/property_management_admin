package com.property.management.service.impl;

import com.property.management.dto.*;
import com.property.management.model.Owner;
import com.property.management.repository.OwnerRepository;
import com.property.management.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public ApiResponseDTO signUp(OwnerSignUpRequestDTO request) {
        if (ownerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        Owner owner = Owner.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .personalAddress(request.getPersonalAddress())
                .mobilePrimary(request.getMobilePrimary())
                .mobileSecondary(request.getMobileSecondary())
                .build();
        ownerRepository.save(owner);
        return new ApiResponseDTO("Successfully registered");
    }

    @Override
    public SignInResponseDTO signIn(OwnerSignInRequestDTO request) {
        Optional<Owner> ownerOpt = ownerRepository.findByEmail(request.getEmailOrName());
        if (ownerOpt.isEmpty()) {
            ownerOpt = ownerRepository.findByName(request.getEmailOrName());
        }
        Owner owner = ownerOpt.orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(request.getPassword(), owner.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return new SignInResponseDTO(owner.getId());
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id);
    }
}