package com.ritik.CrowdShield.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MetricDTO {
    private int crowdCount;
    private double flowSpeed;
    private double riskScore;
}
