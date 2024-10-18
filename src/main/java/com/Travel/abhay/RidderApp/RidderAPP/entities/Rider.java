package com.Travel.abhay.RidderApp.RidderAPP.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private Double rating;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;


}
