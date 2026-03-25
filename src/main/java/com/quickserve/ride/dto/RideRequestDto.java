package com.quickserve.ride.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RideRequestDto {
    @NotBlank(message = "Pickkup Location is required")
    private String pickupLocation;

    @NotBlank(message = "Drop Location is required")
    private String dropLocation;
}
