package com.complaint5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.complaint5.models.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    Mensagem findByMensagem(String mensagem);
}
