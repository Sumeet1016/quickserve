package com.quickserve.rider.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RiderOnboardRequest {

    @NotBlank(message = "License number is required")
private String liceneseNumber;


@NotBlank(message = "Vehicle type is required")
private String vehicleType;

}
