package com.example.api.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sos_alert_entity")
public class SosAlertEntity {

    private Long userId;
    private double latitude;
    private double longitude;
    private boolean resolved;
    private LocalDateTime timestamp;
    @Id
    private Long id;



}