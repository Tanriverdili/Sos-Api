package com.example.api.service;




import com.example.api.dto.SosRequest;
import com.example.api.entity.ContactEntity;
import com.example.api.entity.SosAlertEntity;
import com.example.api.repository.ContactRepository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class SosService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final SimpMessagingTemplate messagingTemplate;
    private final ContactRepository contactRepository;
    private final EmailService emailService;
    private final SmsService smsService;

    public SosService(RedisTemplate<String, Object> redisTemplate, SimpMessagingTemplate messagingTemplate, ContactRepository contactRepository, EmailService emailService, SmsService smsService) {
        this.redisTemplate = redisTemplate;
        this.messagingTemplate = messagingTemplate;
        this.contactRepository = contactRepository;
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public void triggerSOS(SosRequest request) {

        SosAlertEntity alert = SosAlertEntity.builder()
                .userId(request.getUserId())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .resolved(false)
                .timestamp(LocalDateTime.now())
                .build();

        redisTemplate.opsForValue().set("sos:" + request.getUserId(), alert, 3, TimeUnit.MINUTES);


        List<ContactEntity> contacts = contactRepository.findByUserId(request.getUserId());
        for (ContactEntity contact : contacts) {
            messagingTemplate.convertAndSend("/topic/alerts/" + contact.getId(), alert);
        }


        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            SosAlertEntity savedAlert = (SosAlertEntity) redisTemplate.opsForValue()
                    .get("sos:" + request.getUserId());
            if (savedAlert != null && !savedAlert.isResolved()) {
                contacts.forEach(c -> {
                    emailService.sendEmail(c.getEmail(), "SOS Alert!", "Your contact sent an SOS!");
                    smsService.sendSms(c.getPhone(), "Your contact sent an SOS!");
                });
            }
        }, 3, TimeUnit.MINUTES);
    }
}