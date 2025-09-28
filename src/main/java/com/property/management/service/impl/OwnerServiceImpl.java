package com.property.management.service.impl;

import com.property.management.dto.ApiResponseDTO;
import com.property.management.dto.OwnerSignInRequestDTO;
import com.property.management.dto.OwnerSignUpRequestDTO;
import com.property.management.dto.SignInResponseDTO;
import com.property.management.model.Owner;
import com.property.management.repository.OwnerRepository;
import com.property.management.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public ApiResponseDTO signUp(OwnerSignUpRequestDTO request) {
        if (ownerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        Owner owner = Owner.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .personalAddress(request.getAddress())
                .mobilePrimary(String.valueOf(request.getMobileNumber().getPrimary()))
                .mobileSecondary(String.valueOf(request.getMobileNumber().getSecondary()))
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
        if (!StringUtils.pathEquals(request.getPassword(), owner.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return new SignInResponseDTO(owner.getId(), owner.getName());
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return ownerRepository.findById(id);
    }
}