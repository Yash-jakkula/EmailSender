package com.EMAIL.demo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
@Service
public class EmailSender {
    @Autowired
    private JavaMailSender mailSender;
    public EmailSender(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }
    @Async
    public void sendEmail(String email,String subject,String Content) throws NoSuchMessageException, UnsupportedEncodingException{
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("yashjksoft@gmail.com");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(Content,true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
