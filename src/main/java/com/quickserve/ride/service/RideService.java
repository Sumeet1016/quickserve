package com.quickserve.ride.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.quickserve.ride.dto.RideRequestDto;
import com.quickserve.ride.dto.RideResponseDto;
import com.quickserve.ride.entity.Ride;
import com.quickserve.ride.entity.RideStatus;
import com.quickserve.ride.repository.RideRepository;
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
        .passenger(user)
        .pickupLocation(request.getPickupLocation())
        .dropLocation(request.getDropLocation())
        .build();

        rideRepository.save(ride);

        return "Ride requested sucessfully";
    }

    public List<RideResponseDto> getAvailableRides(){
        return rideRepository
        .findByStatus(RideStatus.REQUESTED)
        .stream()
        .map(ride->RideResponseDto.builder()
        .riderId(ride.getId())
        .passengerEmail(ride.getPassenger().getEmail())
        .pickupLocation(ride.getPickupLocation())
        .dropLocation(ride.getDropLocation())
        .status(ride.getStatus().name())
        .build()
    )
    .collect(Collectors.toList());
    }
}
