package com.quickserve.ride.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RideResponseDto {
    private UUID riderId;
    private String passengerEmail;
    private String pickupLocation;
    private String dropLocation;
    private String status;
}
