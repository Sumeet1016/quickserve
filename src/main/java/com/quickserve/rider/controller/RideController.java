package com.quickserve.rider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quickserve.rider.dto.RideRequestDto;
import com.quickserve.rider.service.RideService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/rides")
@RequiredArgsConstructor
public class RideController {
    
    private final RideService rideService;

    @PostMapping("/request")
    public String requestRide(@Valid @RequestBody RideRequestDto request) {
        
        
        return rideService.requestRide(request);
    }
    
}
