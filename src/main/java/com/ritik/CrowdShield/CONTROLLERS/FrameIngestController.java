package com.ritik.CrowdShield.CONTROLLERS;

import com.ritik.CrowdShield.MODELS.FrameData;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/frame")
public class FrameIngestController {

    private final SimpMessagingTemplate messagingTemplate;

    public FrameIngestController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @PostMapping("/ingest")
    public ResponseEntity<String> ingestFrame(@RequestBody FrameData frame) {

        // 1. Broadcast metrics card data
        messagingTemplate.convertAndSend(
                "/topic/metrics",
                (Object) Map.of(
                        "crowdCount", frame.getCrowdCount(),
                        "flowSpeed", frame.getFlowSpeed(),
                        "riskScore", frame.getRiskScore()
                )
        );

        // 2. Broadcast heatmap
        messagingTemplate.convertAndSend(
                "/topic/heatmap",
                (Object) Map.of(
                        "heatmapGrid", frame.getHeatmapGrid()
                )
        );

        // 3. If riskScore > 70 send alert
        if (frame.getRiskScore() > 70) {
            messagingTemplate.convertAndSend(
                    "/topic/alerts",
                    (Object) Map.of(
                            "message", "⚠ HIGH RISK DETECTED",
                            "riskScore", frame.getRiskScore(),
                            "timestamp", LocalDateTime.now().toString()
                    )
            );
        }

        return ResponseEntity.ok("Frame received successfully");
    }
}
