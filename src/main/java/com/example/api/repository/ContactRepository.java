package com.example.api.repository;
import com.example.api.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    List<ContactEntity> findByUser_Id(Long userId);
    List<ContactEntity> findByEmail(String email);
    @Query("SELECT c FROM ContactEntity c WHERE c.email = :email")
    List<ContactEntity> findContactsByEmail(@Param("email") String email);
}
