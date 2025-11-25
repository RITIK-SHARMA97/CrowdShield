package com.ritik.CrowdShield.REPOSITORY;

import com.ritik.CrowdShield.MODELS.Alert;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert,Long> {
   Alert findTopByOrderByTimestampDesc();
}

// custom query method tp find the single latest alert
//most recent alert from the database