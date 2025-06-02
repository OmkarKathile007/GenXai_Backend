package com.GenX_Backend.GenX_Backend.controller;


import com.GenX_Backend.GenX_Backend.service.AIService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.GenX_Backend.GenX_Backend.service.AIService;

import java.util.Map;

@Controller
@CrossOrigin
@RestController
@RequestMapping("/api/ai")
@AllArgsConstructor
public class Aicontroller {

    @Autowired
    private final AIService aiService;
    public Aicontroller(AIService aiService) { this.aiService = aiService; }

    @PostMapping("/summarize")
    public String summarize(@RequestBody Map<String, String> payload) {
        String text = payload.get("text");
        if (text == null || text.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'text' is required");
        }
        try {
            return aiService.getSummarizedResponse(text);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Summarize error: " + e.getMessage(), e
            );
        }
    }

    @PostMapping("/convert")
    public String convert(@RequestBody Map<String, String> payload) {
        String inputLanguage = payload.get("inputLanguage");
        String outputLanguage=payload.get("outputLanguage");
        if (inputLanguage == null || inputLanguage.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'text' is required");
        }
        try {
            return aiService.getConvertedResponse(inputLanguage,outputLanguage);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Convert error: " + e.getMessage(), e
            );
        }
    }

    @PostMapping("/letter")
    public String letter(@RequestBody Map<String, String> payload) {
        String companyName = payload.get("companyName");

        String jobTitle=payload.get("jobTitle");
        String jobDescription=payload.get("jobDescription");
        if (companyName == null || jobDescription==null|| jobTitle==null||companyName.trim().isEmpty()||jobTitle.trim().isEmpty()||jobDescription.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'text' is required");
        }
        try {
            return aiService.getLetterResponse(companyName,jobTitle,jobDescription);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Letter error: " + e.getMessage(), e
            );
        }
    }

    @PostMapping("/roadmap")
    public String roadmap(@RequestBody Map<String, String> payload) {
        String text = payload.get("question");
        if (text == null || text.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'text' is required");
        }
        try {
            return aiService.getRoadmapResponse(text);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Roadmap error: " + e.getMessage(), e
            );
        }
    }

    @PostMapping("/text")
    public String improve(@RequestBody Map<String, String> payload) {
        String text = payload.get("text");
        if (text == null || text.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'text' is required");
        }
        try {
            return aiService.getImprovedResponse(text);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Improve error: " + e.getMessage(), e
            );
        }
    }

    @PostMapping("/email")
    public String email(@RequestBody Map<String, String> payload) {
        String text = payload.get("subject");
        String email=payload.get("toEmail");
        String recipient=payload.get("recipientName");
        if (text == null || text.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'text' is required");
        }
        try {
            return aiService.getEmailResponse(text,email,recipient);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Email error: " + e.getMessage(), e
            );
        }
    }
}
