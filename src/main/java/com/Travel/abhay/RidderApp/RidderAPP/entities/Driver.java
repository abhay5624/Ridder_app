package com.Travel.abhay.RidderApp.RidderAPP.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private Double rating;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Boolean available;

    private String vehicleId;
    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point currentLocation;


}
