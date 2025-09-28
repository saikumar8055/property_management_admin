package com.property.management.controller;

import com.property.management.dto.ApiResponseDTO;
import com.property.management.dto.OwnerSignInRequestDTO;
import com.property.management.dto.OwnerSignUpRequestDTO;
import com.property.management.dto.SignInResponseDTO;
import com.property.management.service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final OwnerService ownerService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDTO> signUp(@Valid @RequestBody OwnerSignUpRequestDTO request) {
        return ResponseEntity.ok(ownerService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponseDTO> signIn(@Valid @RequestBody OwnerSignInRequestDTO request) {
        return ResponseEntity.ok(ownerService.signIn(request));
    }
}