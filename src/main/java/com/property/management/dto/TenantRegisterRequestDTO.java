package com.property.management.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class TenantRegisterRequestDTO {

    @NotNull
    private Long userId;
    @NotNull
    private Long propertyId;
    @NotBlank
    private String roomNo;
    @NotBlank
    private String name;
    @NotBlank
    private String personalAddress;
    @NotBlank
    private String idType;
    @NotBlank
    private String idValue;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String mobilePrimary;
    private String mobileSecondary;
    @NotNull
    private Double rentAmount;
    @NotNull
    private Double advancePaid;
    @NotBlank
    private String checkinDate;
    private String checkoutDate;
}