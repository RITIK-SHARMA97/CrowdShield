package com.ritik.CrowdShield.CONTROLLER;

import com.ritik.CrowdShield.MODELS.CrowdMetrics;
import com.ritik.CrowdShield.REPOSITORY.CrowdMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
public class MetricsController {

    private final CrowdMetricRepository repo;

    @GetMapping("/latest")
    public CrowdMetrics getLatestMetrics() {
        return repo.findTopByOrderByTimestampDesc();
    }
}
