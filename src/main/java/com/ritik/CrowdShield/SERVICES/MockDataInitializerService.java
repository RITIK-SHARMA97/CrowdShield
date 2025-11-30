package com.ritik.CrowdShield.SERVICES;

import com.ritik.CrowdShield.MODELS.Alert;
import com.ritik.CrowdShield.MODELS.CrowdMetrics;
import com.ritik.CrowdShield.REPOSITORY.AlertRepository;
import com.ritik.CrowdShield.REPOSITORY.CrowdMetricRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MockDataInitializerService {

    private final CrowdMetricRepository metricRepo;
    private final AlertRepository alertRepo;

    @PostConstruct
    public void init() {

        // Insert High Risk example
        CrowdMetrics m = new CrowdMetrics();
        m.setCrowdCount(125);
        m.setFlowSpeed(1.0);
        m.setRiskScore(100.0);
        m.setTimestamp(LocalDateTime.now());
        metricRepo.save(m);

        // Insert initial alert
        Alert alert = new Alert();
        alert.setMessage("⚠ HIGH RISK DETECTED – Immediate Action Required!");
        alert.setTimestamp(LocalDateTime.now());
        alertRepo.save(alert);

        System.out.println("Mock high-risk data inserted.");
    }
}
