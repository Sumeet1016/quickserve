package com.quickserve.rider.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.quickserve.rider.dto.RideRequestDto;
import com.quickserve.rider.entity.Ride;
import com.quickserve.rider.repository.RideRepository;
import com.quickserve.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RideService {
    
    private final RideRepository rideRepository;

    public String requestRide(RideRequestDto request){
        User user=(User) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();

        Ride ride=Ride.builder()
        .passanger(user)
        .pickupLocation(request.getPickupLocation())
        .dropLocation(request.getDropLocation())
        .build();

        rideRepository.save(ride);

        return "Ride requested sucessfully";
    }
}
