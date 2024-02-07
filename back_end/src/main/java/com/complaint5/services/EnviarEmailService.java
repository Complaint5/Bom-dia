package com.complaint5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.complaint5.dto.EmailDto;
import com.complaint5.models.Email;
import com.complaint5.models.Mensagem;
import com.complaint5.repositories.EmailRepository;
import com.complaint5.repositories.MensagemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnviarEmailService {
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private MensagemRepository mensagemRepository;
    private final JavaMailSender sender;
    @Value("${email}")
    private String email;
    @Value("${ip}")
    private String ip;
    private final String subjectBomDia = "Bom Dia!";
    private final String subjectCadastro = "Email Cadastrado com sucesso!";
    private final Mensagem mensagemCadastro = new Mensagem(
            "Seu email foi cadastrado com sucesso, para receber emails de bom dia, todos os dias as 6 horas da manhã!");
    private final String cancelamento = "\n\nDeseja desativar as mensagens de bom dia?\nClick no link: " + ip + "/email/atualizar/";

    @Scheduled(fixedDelay = 10000, initialDelay = 15000) // cron = "0 0 6 1/1 * ?"
    @Async
    public void enviarEmail() {
        List<Email> emails = emailRepository.findAllByEnviarTrue();
        List<Mensagem> mensagens = mensagemRepository.findAll();

        if (!emails.isEmpty() && !mensagens.isEmpty()) {
            Mensagem mensagem = mensagens.get((int) (Math.random() * mensagens.size()));
            for (Email email : emails) {
                email(email, subjectBomDia, mensagem);
            }
        }
    }

    @Async
    public void enviarEmailCadastro(EmailDto emailDto) {
        Email email = emailRepository.findByEmail(emailDto.email());
        if (email != null) {
            email(email, subjectCadastro, mensagemCadastro);
        }
    }

    private void email(Email email, String subject, Mensagem mensagem) {
        try {
            SimpleMailMessage menssagem = new SimpleMailMessage();

            menssagem.setFrom(this.email);
            menssagem.setTo(email.getEmail());
            menssagem.setSubject(subject);
            menssagem.setText(mensagem.getMensagem() + cancelamento + email.getId());

            sender.send(menssagem);
        } catch (MailException e) {
        }
    }
}
