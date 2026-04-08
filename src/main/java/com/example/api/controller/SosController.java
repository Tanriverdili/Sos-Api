package com.example.api.controller;
import com.example.api.service.SosService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import com.example.api.dto.SosRequest;
import com.example.api.dto.SosResponse;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/sos")
@RequiredArgsConstructor
public class SosController {

    private final SosService sosService;

    @PostMapping("/send")
    public ResponseEntity<SosResponse> sendSOS(@RequestBody SosRequest request) {
        sosService.triggerSOS(request);
        return ResponseEntity.ok(new SosResponse("SOS sent successfully"));
    }
}