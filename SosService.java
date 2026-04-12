package com.example.api.service;
import com.example.api.entity.ContactEntity;
import com.example.api.entity.SosAlertEntity;
import com.example.api.repository.ContactRepository;
import com.example.api.repository.SosAlertRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SosService {
    private final ContactRepository contactRepository;
    private final SosAlertRepository sosAlertRepository;
    private final EmailService emailService;
    private final SmsService smsService;

    public SosService(ContactRepository contactRepository,
                      SosAlertRepository sosAlertRepository,
                      EmailService emailService,
                      SmsService smsService) {
        this.contactRepository = contactRepository;
        this.sosAlertRepository = sosAlertRepository;
        this.emailService = emailService;
        this.smsService = smsService;
    }

    public List<String> triggerSOS(Long userId) {
        System.out.println("USER ID: " + userId);


        List<ContactEntity> contacts =
                contactRepository.findByUser_Id(userId);
        System.out.println("CONTACTS FOUND: " + contacts.size());

        SosAlertEntity alert = new SosAlertEntity();
        alert.setResolved(false);
        alert.setTimestamp(LocalDateTime.now());
        sosAlertRepository.save(alert);


        List<String> result = new ArrayList<>();

        for (ContactEntity c : contacts) {
            System.out.println("Sending to: " + c.getEmail());

            emailService.sendEmail(
                    c.getEmail(),
                    "🚨 SOS ALERT!",
                    "Your contact sent an SOS alert!"
            );
            smsService.sendSms(
                    c.getPhone(),
                    "🚨 SOS ALERT!"
            );
            result.add("Sent to: " + c.getEmail());
        }
        return result;
    }
    public List<SosAlertEntity> getAlertsForContact(String email) {

        List<ContactEntity> contacts =
                contactRepository.findByEmail(email);

        if (contacts.isEmpty()) {
            throw new RuntimeException("Contact tapılmadı");
        }

        ContactEntity contact = contacts.get(0);

        return sosAlertRepository.findByContact(contact);
    }
}

