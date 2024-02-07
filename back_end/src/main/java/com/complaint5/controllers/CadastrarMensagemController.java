package com.complaint5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.complaint5.dto.MensagemDto;
import com.complaint5.services.MensagemService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cadastrar-mensagem")
public class CadastrarMensagemController {
    @Autowired
    private MensagemService mensagemService;

    @PostMapping
    @Transactional
    public ResponseEntity<MensagemDto> cadastrarMensagem(@RequestBody @Valid MensagemDto mensagem) {
        if (mensagemService.salvarMensagem(mensagem)) {
            return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
        }
        return ResponseEntity.ok(mensagem);
    }
}
