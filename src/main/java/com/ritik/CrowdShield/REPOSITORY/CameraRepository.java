package com.ritik.CrowdShield.REPOSITORY;

import com.ritik.CrowdShield.MODELS.Camera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraRepository  extends JpaRepository<Camera,Long> {


}
