package com.example.api.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sos_alert_entity")
public class SosAlertEntity {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;
    private double latitude;
    private double longitude;
    private boolean resolved;
    private LocalDateTime timestamp;

    @ManyToOne
    private ContactEntity contact;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}

