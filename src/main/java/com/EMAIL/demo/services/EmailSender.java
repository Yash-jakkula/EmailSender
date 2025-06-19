package com.EMAIL.demo.services;

import com.EMAIL.demo.Repositories.Background;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Service
public class EmailSender {
    @Autowired
    private JavaMailSender mailSender;
    private Logger log = LoggerFactory.getLogger(EmailSender.class);
    public EmailSender(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }


    @Async
    public void sendEmail(String email,String subject,String Content) throws NoSuchMessageException, UnsupportedEncodingException{
        MimeMessage message = mailSender.createMimeMessage();
        log.info("mail invoked");
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("yashjksoft@gmail.com");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(Content,true);
            mailSender.send(message);
            log.info("final step of email exceution done ");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Async
    public void emailSendingGroup(List<Map<String,String>> request){
        log.info("request {}",request);
        for (Map<String, String> stringStringMap : request) {
            String email = stringStringMap.get("email");
            String subject = stringStringMap.get("subject");
            String content = stringStringMap.get("Content");
            try {
                log.info("sending mails {}",stringStringMap);
                sendEmail(email, subject, content);
            } catch (UnsupportedEncodingException ex) {
                System.out.println("failed to send email : " + ex.getMessage() + email);
            }
        }
        System.out.println("Email Sent Successfully");
    }
}
