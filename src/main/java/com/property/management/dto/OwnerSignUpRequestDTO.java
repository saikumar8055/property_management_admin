package com.property.management.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class OwnerSignUpRequestDTO {

    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String personalAddress;
    @NotBlank
    private String mobilePrimary;
    private String mobileSecondary;
}