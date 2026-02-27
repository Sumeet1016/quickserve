package com.quickserve.rider.service;

import com.quickserve.rider.dto.RiderOnboardRequest;
import com.quickserve.rider.entity.RiderProfile;
import com.quickserve.rider.repository.RiderProfileRepository;
import com.quickserve.user.entity.Role;
import com.quickserve.user.entity.User;
import com.quickserve.user.repsoitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RiderService {

    private final RiderProfileRepository riderProfileRepository;
    private final UserRepository userRepository;

    public String onboardRider(RiderOnboardRequest request) {
        User authenticatedUser=(User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if(authenticatedUser.getRole()== Role.RIDER){
            throw new RuntimeException("User is already a rider");
        }

        RiderProfile riderProfile=RiderProfile.builder()
        .user(authenticatedUser)
                .licenseNumber(request.getLiceneseNumber())
                .vehicleType(request.getVehicleType())
                .isAvailable(true)
                .build();

        riderProfileRepository.save(riderProfile);

        authenticatedUser.setRole(Role.RIDER);
        userRepository.save(authenticatedUser);
        return "Rider onboarded successfully ";
    }
}
