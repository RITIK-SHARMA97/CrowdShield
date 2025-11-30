package com.ritik.CrowdShield.CONFIG;

import jakarta.annotation.PostConstruct;
import nu.pattern.OpenCV;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenCVConfig {
    @PostConstruct
    public  void init(){
        //launch the correct openCV native library
        System.out.println("Loading OpenCV libraries");
        OpenCV.loadLocally();
        System.out.println("OpenCV loaded successfully");
    }

}
