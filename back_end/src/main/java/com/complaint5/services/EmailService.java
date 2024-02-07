package com.complaint5.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complaint5.dto.EmailDto;
import com.complaint5.models.Email;
import com.complaint5.repositories.EmailRepository;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private EnviarEmailService emailService;

    public boolean salvarEmail(EmailDto emailDto) {
        Email email = emailRepository.findByEmail(emailDto.email());
        if (email == null) {
            emailRepository.save(new Email(emailDto.email(), true));
            emailService.enviarEmailCadastro(emailDto);
            return true;
        } else {
            email.setEnviar(true);
            emailRepository.save(email);
            return false;
        }
    }

    public boolean atualizarStatus(EmailDto emailDto) {
        Email email = emailRepository.findByEmail(emailDto.email());
        if (email != null) {
            email.setEnviar(emailDto.enviar());
            emailRepository.save(email);
            return true;
        }
        return false;
    }

    public boolean atualizarStatus(UUID uuid) {
        Optional<Email> email = emailRepository.findById(uuid);
        if (email != null) {
            email.get().setEnviar(false);
            emailRepository.save(email.get());
            return true;
        }
        return false;
    }

    public EmailDto buscarEmail(String email){
        Email emailResponse = emailRepository.findByEmail(email);
        if (emailResponse != null) {
            return new EmailDto(emailResponse.getEmail(), emailResponse.getEnviar());
        }
        return null;
    }
}
