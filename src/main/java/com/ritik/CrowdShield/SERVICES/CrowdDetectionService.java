package com.ritik.CrowdShield.SERVICES;

import com.ritik.CrowdShield.DTO.HeatMapResponse;
import com.ritik.CrowdShield.MODELS.CrowdMetrics;
import com.ritik.CrowdShield.REPOSITORY.AlertRepository;
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
    private  final AlertService alertService;
    // used to send real time update via websocket
    private final SimpMessagingTemplate webSocket;



    // mocks the process of frame analysis to detect crowd metrics and update the system

    public void detect(MultipartFile file){
            // mocking frame analysis with openCv
        Random rand = new Random();
        int crowdCount = rand.nextInt(100) +50; // mocks crowd count (50 to 149)
        double flowSpeed = rand.nextDouble()*2;// mocks flow Speed (0.0 to 2.0)

        // 1 calculate risk
        double risk = esrsService.calculateRisk(crowdCount,flowSpeed);

        //2  persist Metrics
        CrowdMetrics m = new CrowdMetrics();
        m.setCrowdCount(crowdCount);
        m.setFlowSpeed(flowSpeed);
        m.setRiskScore(Math.min(risk,100.0)); // cap risk at 100
        m.setTimestamp(LocalDateTime.now());
        metricsRepo.save(m);

        // 3 send real rime metric update to dashboard
        webSocket.convertAndSend("topic/metric",m);

        // 4  send real time heatmap update (mock)
        // a simple 10x10 heatmap grid
        int [] [] heatmap = new int [10][10];
        int density =(int) (Math.min(risk,100.0)/10.0);
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                heatmap[i][j]= rand.nextInt(density+1);
            }
        }
        int densityLevel = risk > 70 ? 2 : (risk >40 ? 1:0);
        webSocket.convertAndSend("/topic/heatmap" , new HeatMapResponse(heatmap,densityLevel));
        // check and trigger Alert /PA System

        alertService.checkAndSendAlert(risk);





    }








}
