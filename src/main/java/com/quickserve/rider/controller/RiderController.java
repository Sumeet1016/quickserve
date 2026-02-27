package com.quickserve.rider.controller;

import com.quickserve.rider.dto.RiderOnboardRequest;
import com.quickserve.rider.service.RiderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rider")
@RequiredArgsConstructor
public class RiderController {

    private final RiderService riderService;

//

    @GetMapping("/dashboard")
    public String dashbord(){
        return "Rider dashboard working";
    }

    @PostMapping("/onboard")
    public String onboard(@Valid @RequestBody RiderOnboardRequest request){
        return riderService.onboardRider(request);
    }
}
