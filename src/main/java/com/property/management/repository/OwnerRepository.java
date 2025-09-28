package com.property.management.repository;

import com.property.management.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Optional<Owner> findByEmail(String email);
    Optional<Owner> findByName(String name);
}