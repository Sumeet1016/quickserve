package com.quickserve.ride.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quickserve.ride.entity.Ride;
import com.quickserve.ride.entity.RideStatus;

public interface  RideRepository extends JpaRepository<Ride,UUID> {
    List<Ride> findByStatus(RideStatus status);
}
