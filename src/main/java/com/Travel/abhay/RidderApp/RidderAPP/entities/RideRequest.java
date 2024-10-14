package com.Travel.abhay.RidderApp.RidderAPP.entities;

import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.PaymentMethod;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickupLocation;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropOffLocation ;

    @CreationTimestamp
    private LocalDateTime RequestedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Ridder ridder;

    @Enumerated(EnumType.STRING)
    private RideRequestStatus rideRequestStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private double fare;

}
