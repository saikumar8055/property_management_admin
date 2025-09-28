package com.property.management.service.impl;

import com.property.management.dto.*;
import com.property.management.model.*;
import com.property.management.repository.*;
import com.property.management.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final OwnerRepository ownerRepository;
    private final RoomRepository roomRepository;
    private final TenantRepository tenantRepository;

    @Override
    public Long registerProperty(PropertyRegisterRequestDTO request) {
        Owner owner = ownerRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        Property property = Property.builder()
                .houseNo(request.getHouseNo())
                .address(request.getAddress())
                .owner(owner)
                .rooms(new ArrayList<>())
                .build();
        for (PropertyRegisterRequestDTO.RoomInfoDTO roomDto : request.getRoomInfo()) {
            Room room = Room.builder()
                    .roomNo(roomDto.getRoomNo())
                    .roomType(roomDto.getRoomType())
                    .property(property)
                    .build();
            property.getRooms().add(room);
        }
        propertyRepository.save(property);
        return property.getId();
    }

    @Override
    public void registerTenant(TenantRegisterRequestDTO request) {
        Room room = roomRepository.findByRoomNoAndPropertyId(request.getRoomNo(), request.getPropertyId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
        if (room.getTenant() != null) {
            throw new RuntimeException("Room already has a tenant");
        }
        Tenant tenant = Tenant.builder()
                .name(request.getName())
                .personalAddress(request.getPersonalAddress())
                .idType(request.getIdType())
                .idValue(request.getIdValue())
                .email(request.getEmail())
                .mobilePrimary(request.getMobilePrimary())
                .mobileSecondary(request.getMobileSecondary())
                .rentAmount(request.getRentAmount())
                .advancePaid(request.getAdvancePaid())
                .checkinDate(request.getCheckinDate())
                .checkoutDate(request.getCheckoutDate())
                .room(room)
                .build();
        room.setTenant(tenant);
        roomRepository.save(room);
    }

    @Override
    public List<PropertyDetailsResponseDTO> getPropertiesByUserId(Long userId) {
        List<Property> properties = propertyRepository.findByOwnerId(userId);
        return properties.stream().map(this::toDto).collect(Collectors.toList());
    }

    private PropertyDetailsResponseDTO toDto(Property property) {
        PropertyDetailsResponseDTO dto = new PropertyDetailsResponseDTO();
        dto.setPropertyId(property.getId());
        dto.setHouseNo(property.getHouseNo());
        dto.setAddress(property.getAddress());
        List<PropertyDetailsResponseDTO.RoomInfo> roomInfos = new ArrayList<>();
        for (Room room : property.getRooms()) {
            PropertyDetailsResponseDTO.RoomInfo roomInfo = new PropertyDetailsResponseDTO.RoomInfo();
            roomInfo.setRoomNo(room.getRoomNo());
            roomInfo.setRoomType(room.getRoomType());
            if (room.getTenant() != null) {
                PropertyDetailsResponseDTO.TenantInfo tenantInfo = new PropertyDetailsResponseDTO.TenantInfo();
                tenantInfo.setId(room.getTenant().getId());
                tenantInfo.setName(room.getTenant().getName());
                tenantInfo.setPersonalAddress(room.getTenant().getPersonalAddress());
                PropertyDetailsResponseDTO.IdDetails idDetails = new PropertyDetailsResponseDTO.IdDetails();
                idDetails.setIdType(room.getTenant().getIdType());
                idDetails.setIdValue(room.getTenant().getIdValue());
                tenantInfo.setIdDetails(idDetails);
                tenantInfo.setEmail(room.getTenant().getEmail());
                tenantInfo.setMobilePrimary(room.getTenant().getMobilePrimary());
                tenantInfo.setMobileSecondary(room.getTenant().getMobileSecondary());
                tenantInfo.setRentAmount(room.getTenant().getRentAmount());
                tenantInfo.setAdvancePaid(room.getTenant().getAdvancePaid());
                tenantInfo.setCheckinDate(room.getTenant().getCheckinDate());
                tenantInfo.setCheckoutDate(room.getTenant().getCheckoutDate());
                roomInfo.setTenantInfo(tenantInfo);
            }
            roomInfos.add(roomInfo);
        }
        dto.setRoomInfo(roomInfos);
        return dto;
    }
}

