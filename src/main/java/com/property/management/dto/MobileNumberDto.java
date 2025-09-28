package com.property.management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MobileNumberDto {

    @NotBlank
    private Long primary;
    private Long secondary;
}
