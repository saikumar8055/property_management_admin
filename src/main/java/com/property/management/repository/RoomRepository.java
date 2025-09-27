package com.property.management.repository;

import com.property.management.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByRoomNoAndPropertyId(String roomNo, Long propertyId);
}