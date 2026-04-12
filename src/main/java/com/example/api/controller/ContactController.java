package com.example.api.controller;
import com.example.api.entity.ContactEntity;
import com.example.api.service.ContactService;
import com.example.api.service.SosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;
    private final SosService sosService;

    @PostMapping("/add")
    public ResponseEntity<ContactEntity> addContact(
            @RequestBody ContactEntity request,
            Principal principal
    ) {
        return ResponseEntity.ok(
                contactService.addContact(principal.getName(), request)
        );
    }
    @GetMapping
    public ResponseEntity<List<ContactEntity>> getContacts(@RequestParam String email) {
        return ResponseEntity.ok(contactService.getContacts(email));
    }
}



