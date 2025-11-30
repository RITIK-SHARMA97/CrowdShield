package com.ritik.CrowdShield.CONTROLLERS;

import com.ritik.CrowdShield.SERVICES.VideoIngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/camera")
@RequiredArgsConstructor
public class VideoIngestionController {
    private final VideoIngestionService ingestionService;

    @PostMapping("/stream")
    public ResponseEntity<String> handleVideoStream(@RequestParam("frame") MultipartFile frame) {
        if (frame.isEmpty()) {
            return new ResponseEntity<>("Frame file is empty.", HttpStatus.BAD_REQUEST);
        }

        System.out.println("Frame received and processing triggered for file: " + frame.getOriginalFilename());
        ingestionService.processFrame(frame);

        return new ResponseEntity<>("Frame queued for processing.", HttpStatus.ACCEPTED);
    }
}
