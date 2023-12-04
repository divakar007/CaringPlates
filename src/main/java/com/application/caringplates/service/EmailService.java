package com.application.caringplates.service;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public MimeMessage createMimeMessage() {
        return  javaMailSender.createMimeMessage();
    }

    public void send(MimeMessage message) {
        javaMailSender.send(message);
    }
}
