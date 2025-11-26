package com.ritik.CrowdShield.SERVICES;

import org.springframework.stereotype.Service;

@Service
public class SMSService {


    /*
    mocks sending an sms notification to emergency contacts /volunteers

     */

    public void sendSMS(String number, String message){
        System.out.println("SMS sent");
        System.out.println("to" + number);
        System.out.println("message"+message);
        System.out.println("                 ");
    }

}
