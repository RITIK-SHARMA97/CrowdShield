package com.ritik.CrowdShield.MODELS;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvacuationRoute {

    @Id
    @GeneratedValue
    private Long id;

    private String name; //Name of the route

    private String description;//Explanation or notes about the route.

    private double capacityFactor; // how fast people move through it

}

//RouteOptimizationService
//will:

//fetch all routes from DB
//
//compare capacityFactor values
//
//choose safest & fastest route
//
//send route info to:
//
//dashboard
//
//SMS mock service
//
//PA announcements