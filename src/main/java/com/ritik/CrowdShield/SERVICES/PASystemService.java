package com.ritik.CrowdShield.SERVICES;

import org.springframework.stereotype.Service;

@Service
public class PASystemService {
    /*
    Mocks triggering on announcement over a public address system
    @param msg the message to broadcast
    @return confirmation message
     */

    public String broadcast(String msg){
        String confirmation = "PA Announcement Triggered " +msg;
        System.out.println(confirmation);
        return confirmation;
    }





    

}
