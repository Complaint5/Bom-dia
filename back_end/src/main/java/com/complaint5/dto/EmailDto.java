package com.complaint5.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDto(@NotBlank @Email String email, boolean enviar) {
}
