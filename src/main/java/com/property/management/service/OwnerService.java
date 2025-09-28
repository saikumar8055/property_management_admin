package com.property.management.service;

import com.property.management.dto.*;
import com.property.management.model.Owner;
import java.util.Optional;

public interface OwnerService {

    ApiResponseDTO signUp(OwnerSignUpRequestDTO request);
    SignInResponseDTO signIn(OwnerSignInRequestDTO request);
    Optional<Owner> findById(Long id);
}
