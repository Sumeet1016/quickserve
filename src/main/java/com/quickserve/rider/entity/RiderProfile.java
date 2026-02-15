package com.quickserve.rider.entity;

import com.quickserve.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="rider_profiles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RiderProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false,nullable = false)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false,unique = true)
    private User user;

    @Column(name="license_number",nullable = false)
    private String licenseNumber;

    @Column(name="vehicle_type",nullable = false)
    private String vehicleType;

    @Column(name="is_Available",nullable = false)
    private Boolean isAvailable;

    @Column(name="current_Latitude")
    private Double currentLatitude;

    @Column(name="current_Longitude")
    private Double currentLongitude;

    @Column(name="created_at",nullable = false,updatable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @Version
    private Integer version;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if(this.isAvailable==null){
            this.isAvailable=true;
        }
    }

    @PreUpdate
    protected   void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
