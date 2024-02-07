package com.complaint5.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@Getter
@Table(name = "tb_email")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true, updatable = false)
    private UUID id;
    @Column(unique = true)
    private String email;
    private boolean enviar;
    private LocalDateTime horaCadastro;

    public Email(String email, boolean enviar) {
        this.email = email;
        this.horaCadastro = LocalDateTime.now();
        this.enviar = enviar;
    }

    public boolean getEnviar() {
        return enviar;
    }
}
