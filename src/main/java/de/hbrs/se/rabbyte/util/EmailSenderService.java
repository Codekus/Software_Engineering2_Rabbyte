package de.hbrs.se.rabbyte.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailSenderService")
public class EmailSenderService {

    public EmailSenderService(String email, String token) {
        mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(Globals.Email.SUBJECT_REGISTRATION);
        mailMessage.setFrom(Globals.Email.EMAIL_SENDER);
        mailMessage.setFrom(Globals.Email.TEXT_REGISTRATION + token);
    }

    private JavaMailSender javaMailSender;
    private SimpleMailMessage mailMessage;
    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }



    @Async
    public  void sendEmail() {
        javaMailSender.send(mailMessage);
    }




}