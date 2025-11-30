package com.ritik.CrowdShield.CONTROLLERS;

import com.ritik.CrowdShield.SERVICES.PASystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pa")
@RequiredArgsConstructor
public class PAController {

    private final PASystemService paService;

    /*
    endpoint to manually trigger a PA announcement
     */
    @PostMapping("/announce")
    public  String announce(@RequestBody String msg){
        return paService.broadcast(msg);
    }
}
