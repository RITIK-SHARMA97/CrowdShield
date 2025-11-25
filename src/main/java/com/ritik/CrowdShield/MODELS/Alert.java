package com.ritik.CrowdShield.MODELS;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Alert {
    @Id
    @GeneratedValue
    private Long id;
    private String message; // what happened
    private  double riskScore; // how dangerous is it
    private LocalDateTime timestamp; // when did it happen

}
//Spring Boot then saves this Alert into the database, so we can show it on the dashboard or notify police.