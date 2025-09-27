package com.property.management.dto;

import lombok.Data;
import java.util.List;

@Data
public class PropertyDetailsResponseDTO {

    private Long propertyId;
    private String houseNo;
    private String address;
    private List<RoomInfo> roomInfo;

    @Data
    public static class RoomInfo {
        private String roomNo;
        private String roomType;
        private TenantInfo tenantInfo;
    }

    @Data
    public static class TenantInfo {
        private Long id;
        private String name;
        private String personalAddress;
        private IdDetails idDetails;
        private String email;
        private String mobilePrimary;
        private String mobileSecondary;
        private Double rentAmount;
        private Double advancePaid;
        private String checkinDate;
        private String checkoutDate;
    }

    @Data
    public static class IdDetails {
        private String idType;
        private String idValue;
    }
}
