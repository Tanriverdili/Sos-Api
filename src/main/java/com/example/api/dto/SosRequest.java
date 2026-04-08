package com.example.api.dto;



import lombok.Data;

@Data
public class SosRequest {
    private Long userId;
    private double latitude;
    private double longitude;
    private boolean resolved;
}