package com.property.management.controller;

import com.property.management.dto.*;
import com.property.management.service.PropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping
    public ResponseEntity<Long> registerProperty(@Valid @RequestBody PropertyRegisterRequestDTO request) {
        return ResponseEntity.ok(propertyService.registerProperty(request));
    }

    @PostMapping("/{propertyId}/rooms/{roomNo}/tenant")
    public ResponseEntity<ApiResponseDTO> registerTenant(@PathVariable Long propertyId, @PathVariable String roomNo, @Valid @RequestBody TenantRegisterRequestDTO request) {
        propertyService.registerTenant(request);
        return ResponseEntity.ok(new ApiResponseDTO("Tenant registered successfully"));
    }

    @GetMapping
    public ResponseEntity<List<PropertyDetailsResponseDTO>> getProperties(@RequestParam Long userId) {
        return ResponseEntity.ok(propertyService.getPropertiesByUserId(userId));
    }
}