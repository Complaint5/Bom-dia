package com.complaint5.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.complaint5.models.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, UUID> {
    List<Email> findAllByEnviarTrue();

    Email findByEmail(String email);
}
