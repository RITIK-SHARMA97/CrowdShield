package com.ritik.CrowdShield.REPOSITORY;

import com.ritik.CrowdShield.MODELS.CrowdMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrowdMetricRepository  extends JpaRepository<CrowdMetrics,Long> {
    CrowdMetrics findTopByOrderByTimestampDesc();
}
//This repository talks to the CrowdMetrics table and gives  the latest data point for real-time monitoring



// CrowdShield  continuously updates crowd metrics.

//Every time a frame is processed → new CrowdMetrics saved.

// dashboard should show the latest info.

//This method gives exactly that