package com.quickserve.ride.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.quickserve.user.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="rides")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ride {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "passenger_id",nullable = false)
    private User passenger;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="rider_id")
    private User rider;

    @Column(name = "pickup_location", nullable=false)
    private String pickupLocation;

    @Column(name="drop_location", nullable = false)
    private String dropLocation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RideStatus status;

    @Column(name="requested_at",nullable = false)
    private LocalDateTime requestedAt;

    @Column(name="updated_at",nullable = false)
    private LocalDateTime updatedAt;

    @Version
    private Integer version;

    @PrePersist
    protected void onCreate(){
        this.requestedAt=LocalDateTime.now();
        this.updatedAt=LocalDateTime.now();
        this.status=RideStatus.REQUESTED;
    }

    @PreUpdate
    protected  void onUpdate(){
        this.updatedAt=LocalDateTime.now();
    }
}
