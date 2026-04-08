package com.example.api.repository;


import com.example.api.entity.SosAlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository


public interface SosAlertRepository extends JpaRepository<SosAlertEntity, Long> {}