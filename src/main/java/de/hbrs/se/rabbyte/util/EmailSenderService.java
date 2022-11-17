package de.hbrs.se.rabbyte.util;

import de.hbrs.se.rabbyte.dtos.VerificationTokenDTO;

import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


public class EmailSenderService {


    private JavaMailSenderImpl  mailSender;

    private SimpleMailMessage mailMessage;

    public EmailSenderService(VerificationTokenDTO verificationTokenDTO) {
        mailSender = new JavaMailSenderImpl();
        mailMessage = new SimpleMailMessage();
        mailMessage.setTo(verificationTokenDTO.getUser().getEmail());
        mailMessage.setSubject(Globals.Email.SUBJECT_REGISTRATION);
        mailMessage.setFrom(Globals.Email.EMAIL_SENDER);
        mailMessage.setText(Globals.Email.TEXT_REGISTRATION + verificationTokenDTO.getToken());

        mailSender.setHost(Globals.Email.HOST);
        mailSender.setPort(Globals.Email.PORT);
        mailSender.setUsername(Globals.Email.GMAIL);
        mailSender.setPassword(Globals.Email.APP_AUTHENTICATION);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
    }

    public  void sendEmail() {
        mailSender.send(mailMessage);
    }




}