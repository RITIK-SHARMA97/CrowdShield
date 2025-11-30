package com.ritik.CrowdShield.SERVICES;

import com.ritik.CrowdShield.DTO.HeatMapResponse;
import com.ritik.CrowdShield.DTO.MetricDTO;
import com.ritik.CrowdShield.DTO.MetricDTO;
import com.ritik.CrowdShield.MODELS.CrowdMetrics;
import com.ritik.CrowdShield.REPOSITORY.CrowdMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CrowdDetectionService {

    private final ESRSService esrsService;
    private final CrowdMetricRepository metricsRepo;
    private final AlertService alertService;
    private final SimpMessagingTemplate webSocket;

    private final Random rand = new Random();

    public void detect(MultipartFile file) {

        // ------- 1. SIMULATED FRAME ANALYSIS -------
        int crowdCount = rand.nextInt(100) + 50;   // 50–149
        double flowSpeed = rand.nextDouble() * 2;  // 0–2 m/s

        // ------- 2. CALCULATE RISK -------
        double risk = Math.min(esrsService.calculateRisk(crowdCount, flowSpeed), 100.0);

        // ------- 3. SAVE TO DATABASE -------
        CrowdMetrics m = new CrowdMetrics();
        m.setCrowdCount(crowdCount);
        m.setFlowSpeed(flowSpeed);
        m.setRiskScore(risk);
        m.setTimestamp(LocalDateTime.now());
        metricsRepo.save(m);

        // ------- 4. SEND METRICS TO DASHBOARD -------
        MetricDTO dto = new MetricDTO(crowdCount, flowSpeed, risk);
        webSocket.convertAndSend("/topic/metrics", dto);

        // ------- 5. HEATMAP DATA -------
        int[][] heatmap = new int[10][10];
        int density = (int) (risk / 10.0);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                heatmap[i][j] = rand.nextInt(density + 1);
            }
        }

        int densityLevel = risk > 70 ? 2 : (risk > 40 ? 1 : 0);
        webSocket.convertAndSend("/topic/heatmap", new HeatMapResponse(heatmap, densityLevel));

        // ------- 6. CHECK ALERTS -------
        alertService.checkAndSendAlert(risk);
    }
}
