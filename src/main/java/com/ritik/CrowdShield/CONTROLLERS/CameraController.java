package com.ritik.CrowdShield.CONTROLLERS;

import com.ritik.CrowdShield.SERVICES.VideoIngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/camera")
@RequiredArgsConstructor
public class CameraController {


    private final VideoIngestionService videoService;

    /*
    endpoint to simulate receiving a video frame (as a file upload) from a CCTV source
    Usage :POST /camera/stream with multipart form data key 'frame'

     */
    @PostMapping("/frame")
    public ResponseEntity<String> ingestFrame(@RequestParam("frame")MultipartFile frame){
        // the file content is mocked in the service but the file is required by the API
        videoService.processFrame(frame);
        return ResponseEntity.ok("Frame received and processing triggered for: "+frame.getOriginalFilename());
    }










}
