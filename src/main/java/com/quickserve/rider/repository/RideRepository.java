package com.quickserve.rider.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quickserve.rider.entity.Ride;

public interface  RideRepository extends JpaRepository<Ride,UUID> {
    
}
