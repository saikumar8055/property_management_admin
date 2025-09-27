package com.property.management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String personalAddress;
    private String idType;
    private String idValue;
    private String email;
    private String mobilePrimary;
    private String mobileSecondary;
    private Double rentAmount;
    private Double advancePaid;
    private String checkinDate;
    private String checkoutDate;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;
}