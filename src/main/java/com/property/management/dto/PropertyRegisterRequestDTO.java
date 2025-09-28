package com.property.management.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
import java.util.List;

@Data
public class PropertyRegisterRequestDTO {

    @NotNull
    private Long userId;
    @NotBlank
    private String houseNo;
    @NotBlank
    private String address;
    @NotNull
    private List<RoomInfoDTO> roomInfo;

    @Data
    public static class RoomInfoDTO {
        @NotBlank
        private String roomNo;
        @NotBlank
        private String roomType;
    }
}