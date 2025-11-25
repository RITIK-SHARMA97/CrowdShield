package com.ritik.CrowdShield.MODELS;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class CrowdMetrics {

    @Id
    @GeneratedValue
    private Long id; // this is the unique id for each frame analysis

    private int crowdCount;// how many people are detected

    private double flowSpeed;// how fast crowd is moving

    private double riskScore; //store the danger score of our algorithm calculates

    private LocalDateTime timestamp; // the exact time this frame was analyzed
}
