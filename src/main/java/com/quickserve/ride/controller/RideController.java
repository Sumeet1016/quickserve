package com.quickserve.ride.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quickserve.ride.dto.RideRequestDto;
import com.quickserve.ride.dto.RideResponseDto;
import com.quickserve.ride.entity.Ride;
import com.quickserve.ride.service.RideService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideController {
    
    private final RideService rideService;

    @PostMapping("/request")
    public String requestRide(@Valid @RequestBody RideRequestDto request) {
        
        
        return rideService.requestRide(request);
    }
    
    @GetMapping("/available")
    public List<RideResponseDto> getAvailableRides() {
        return rideService.getAvailableRides();
    }
    
}
