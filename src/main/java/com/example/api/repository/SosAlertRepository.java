package com.example.api.repository;
import com.example.api.entity.ContactEntity;
import com.example.api.entity.SosAlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SosAlertRepository extends JpaRepository<SosAlertEntity, Long> {
    List<SosAlertEntity> findByContact(ContactEntity contact);
}