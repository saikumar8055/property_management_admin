package com.property.management.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class OwnerSignInRequestDTO {

    @NotBlank
    private String emailOrName;
    @NotBlank
    private String password;
}

