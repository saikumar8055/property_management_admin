package com.property.management.service;

import com.property.management.dto.*;
import java.util.List;

public interface PropertyService {

    Long registerProperty(PropertyRegisterRequestDTO request);
    void registerTenant(TenantRegisterRequestDTO request);
    List<PropertyDetailsResponseDTO> getPropertiesByUserId(Long userId);
}

