package com.ritik.CrowdShield.SERVICES;

import org.springframework.stereotype.Service;

@Service
public class ESRSService {


    /* calculates the early stampede risk  score (ESRS)
   // rule : risk = (crowd count *0.6) + (flow speed *40)
   // crowd count (e.g, 100) ->60 points
   flow speed (e.g ,1.0) -> 40 points
   max theoretical risk score is >100 copped in detection service

   */

   public double calculateRisk(int crowdCount , double flowSpeed){
       // ESRS formula : density contributes 60% of max , speed contributes 40%
       return  (crowdCount* 0.6) + (flowSpeed *40);
   }








}
