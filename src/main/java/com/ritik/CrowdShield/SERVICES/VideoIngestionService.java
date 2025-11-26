package com.ritik.CrowdShield.SERVICES;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoIngestionService {

    private final CrowdDetectionService detectionService;
    // receives a video frame and delegates processing to detection service
    // in a real system this would handle frame decoding and pre processing



    public void processFrame(MultipartFile file){


        System.out.println("Processing frame from file :" +file.getOriginalFilename());
        detectionService.detect(file);
    }









}
