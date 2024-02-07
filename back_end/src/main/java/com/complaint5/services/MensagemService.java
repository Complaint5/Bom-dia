package com.complaint5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.complaint5.dto.MensagemDto;
import com.complaint5.models.Mensagem;
import com.complaint5.repositories.MensagemRepository;

@Service
public class MensagemService {
    @Autowired
    private MensagemRepository mensagemRepository;

    public boolean salvarMensagem(MensagemDto mensagemDto) {
        if (mensagemRepository.findByMensagem(mensagemDto.mensagem()) == null) {
            mensagemRepository.save(new Mensagem(mensagemDto.mensagem()));
            return true;
        }
        return false;
    }
}
