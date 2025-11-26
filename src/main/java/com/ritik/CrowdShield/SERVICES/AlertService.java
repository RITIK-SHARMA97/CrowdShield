package com.ritik.CrowdShield.SERVICES;

import com.ritik.CrowdShield.MODELS.Alert;
import com.ritik.CrowdShield.REPOSITORY.AlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository repo;
    private final PASystemService paService;
    private final  SMSService smsService;
    private  final SimpMessagingTemplate webSocket;


    /*
    * checks the calculated risk score and trigger alert if necessary
    * */

    public void checkAndSendAlert(double risk){
        if(risk > 70.0){
          String message=  String.format("HIGH RISK ALERT (ESRS : %.3f) !Intiate crowd control procedures",risk);
            // 1 create and persist alert
            Alert alert = new Alert();
            alert.setMessage(message);
            alert.setRiskScore(risk);
            alert.setTimestamp(LocalDateTime.now());
            repo.save(alert);
            // 2 trigger external system
            paService.broadcast("Attention all patrons.Please move slowly and follow the direction of security staff. Clear exits immediately");
            smsService.sendSMS("911",message);
            //  3 send real time alert update to dashboard
            webSocket.convertAndSend("/topic/alerts",alert);

        } else if (risk >40.0) {
            System.out.println("MEDIUM RISK : Risk Score is elevated but manageable" );


        }
    }

    public Alert getLatestAlert(){
        return repo.findTopByOrderByTimestampDesc();
    }





}
