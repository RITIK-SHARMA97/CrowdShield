package com.ritik.CrowdShield.CONTROLLERS;

import com.ritik.CrowdShield.MODELS.Alert;
import com.ritik.CrowdShield.REPOSITORY.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alert")
@RequiredArgsConstructor
public class AlertController {

    private final AlertRepository repo;

    @GetMapping("/latest")
    public Alert getLatestAlert() {
        return repo.findTopByOrderByTimestampDesc();
    }
}
