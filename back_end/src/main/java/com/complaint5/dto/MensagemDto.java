package com.complaint5.dto;

import jakarta.validation.constraints.NotBlank;

public record MensagemDto(@NotBlank String mensagem) {

}
