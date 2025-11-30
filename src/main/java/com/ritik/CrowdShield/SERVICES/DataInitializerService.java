package com.ritik.CrowdShield.SERVICES;

import com.ritik.CrowdShield.MODELS.CrowdMetrics;
import com.ritik.CrowdShield.REPOSITORY.CrowdMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DataInitializerService {
    private final ESRSService esrsService;
    private final AlertService alertService;
    private final CrowdMetricRepository metricRepository;
    /*
    ensure the dashboard to show data

     */
    @EventListener(ApplicationReadyEvent.class)
    public void initializeMockMetrics(){
        System.out.println("Initializing database with mock metric scenario");
        /*scenario 1 low risk
        count =20 flow 0.1->ESRS =16.0

         */
        simulateAndSave(20,0.1,"LOW RISK - NORMAL BASELINE");
        simulateAndSave(85,0.2,"MEDIUM RISK -HIGH DENSITY");
        simulateAndSave(125,1.0,"HIGH RISK -CRITICAL ALERT CONDITION");

    }

    private void simulateAndSave(int crowdCount, double flowSpeed,String scenario){
        double risk = esrsService.calculateRisk(crowdCount,flowSpeed);
        // persist metrics
        CrowdMetrics m = new CrowdMetrics();
        m.setCrowdCount(crowdCount);
        m.setFlowSpeed(flowSpeed);
        m.setRiskScore(Math.min(risk,100.0));
        m.setTimestamp(LocalDateTime.now());
        metricRepository.save(m);

        // trigger alert if risk >70
        alertService.checkAndSendAlert(risk);
        System.out.printf(" > Saved %s : Count =%d,Flow=%.2f,ESRS=5.2f\n",
                scenario,crowdCount,flowSpeed,risk);

    }
}
