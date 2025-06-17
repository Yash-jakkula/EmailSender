package com.EMAIL.demo.services;

import com.EMAIL.demo.Repositories.Background;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
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

    @Async
    public ResponseEntity<String> emailSendingGroup(List<Map<String,String>> request){
        for(int i=0;i<request.size();i++) {
            String email = request.get(i).get("email");
            String subject = request.get(i).get("subject");
            String content = request.get(i).get("body");
            try {

                sendEmail(email, subject, content);
            } catch (UnsupportedEncodingException ex) {
                System.out.println("failed to send email : " + ex.getMessage() + email);
            }
        }
        return ResponseEntity.ok("Email Sent Successfully");
    }
}
