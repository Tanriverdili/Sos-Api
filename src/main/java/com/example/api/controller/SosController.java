package com.example.api.controller;
import com.example.api.entity.SosAlertEntity;
import com.example.api.service.SosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.api.dto.SosRequest;
import com.example.api.dto.SosResponse;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/sos")
@RequiredArgsConstructor
public class SosController {
    private final SosService sosService;

    @PostMapping("/send")
    public ResponseEntity<SosResponse> sendSOS(@RequestBody SosRequest request) {
        sosService.triggerSOS(request.getUserId());
        return ResponseEntity.ok(new SosResponse("SOS sent successfully"));
    }

    @GetMapping("/alerts/{contactId}")
    public ResponseEntity<List<SosAlertEntity>> getAlerts(@PathVariable Long contactId) {

        return ResponseEntity.ok(
                sosService.getAlertsForContact(String.valueOf(contactId))
        );
    }
}

























































