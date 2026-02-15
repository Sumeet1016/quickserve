package com.quickserve.rider.repository;

import com.quickserve.rider.entity.RiderProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RiderProfileRepository extends JpaRepository<RiderProfile, UUID> {

    Optional<RiderProfile> findByUserId(UUID userId);
}
