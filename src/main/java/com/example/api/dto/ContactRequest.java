package com.example.api.dto;
import lombok.Data;

@Data
public class ContactRequest {
    private String name;
    private String email;
    private String phone;
    private Long user_Id;
}