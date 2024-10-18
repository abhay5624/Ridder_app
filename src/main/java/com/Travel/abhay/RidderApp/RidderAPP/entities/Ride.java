package com.Travel.abhay.RidderApp.RidderAPP.entities;

import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.PaymentMethod;
import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickupLocation;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropOffLocation;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private Rider rider;

    @ManyToOne(fetch = FetchType.EAGER)
    private Driver driver;

    private String otp;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private double fare;
    private  LocalDateTime startedAt;
    private  LocalDateTime EndedAt;
}
