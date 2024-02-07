package com.complaint5.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.complaint5.dto.EmailDto;
import com.complaint5.services.EmailService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/email")
public class CadastrarEmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<EmailDto> cadastrarEmail(@RequestBody @Valid EmailDto email) {
        if (emailService.salvarEmail(email)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(email);
        }
        return ResponseEntity.ok(email);
    }

    @PutMapping("/atualizar")
    @Transactional
    public ResponseEntity<EmailDto> atualizarStatus(@RequestBody @Valid EmailDto emailDto) {
        if (emailService.atualizarStatus(emailDto)) {
            return ResponseEntity.ok(emailDto);
        }
        return ResponseEntity.notFound().build();
    } 

    @GetMapping("/atualizar/{status}")
    public ResponseEntity<String> atualizarStatusUri(@PathVariable("status") UUID emailDto) {
        if(emailService.atualizarStatus(emailDto)){
            return ResponseEntity.ok("");
        }
        return ResponseEntity.notFound().build();
    } 
    
    @GetMapping
    public ResponseEntity<EmailDto> buscarEmail(@RequestBody EmailDto email){
        EmailDto emailDto = emailService.buscarEmail(email.email());
        if(emailDto != null){
            return ResponseEntity.ok(emailDto);
        }
        return ResponseEntity.notFound().build();
    }
}
