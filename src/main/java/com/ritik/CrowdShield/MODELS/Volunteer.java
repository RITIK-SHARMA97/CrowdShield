package com.ritik.CrowdShield.MODELS;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class Volunteer {

    @Id
    @GeneratedValue
    private  Long id;

    private String name;

    private String phoneNumber;

    private boolean isAvailable;
}
