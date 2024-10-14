package com.Travel.abhay.RidderApp.RidderAPP.entities;

import com.Travel.abhay.RidderApp.RidderAPP.entities.enums.Roles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "App_Users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String name;
    @Column(unique = true)
    private  String email;

    private String password;

    @ElementCollection(fetch = FetchType.LAZY)
    @Enumerated(EnumType.STRING)
    private Set<Roles> roles;
}
