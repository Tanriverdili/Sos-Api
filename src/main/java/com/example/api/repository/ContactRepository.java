package com.example.api.repository;



import com.example.api.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    List<ContactEntity> findByUserId(Long userId);
}