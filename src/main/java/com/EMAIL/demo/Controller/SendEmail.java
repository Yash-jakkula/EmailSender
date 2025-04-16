package com.EMAIL.demo.Controller;

import com.EMAIL.demo.EmailSender;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SendEmail {
    @Autowired
    private EmailSender emailSender;
    public SendEmail(EmailSender emailSender){
        this.emailSender = emailSender;
    }
    @PostMapping("/sendEmail/list")
    public ResponseEntity<String> emailSending(@RequestBody List<Map<String,String>> request) throws MessagingException, UnsupportedEncodingException{
        for(int i=0;i<request.size();i++) {
            String email = request.get(i).get("email");
            String subject = request.get(i).get("subject");
            String content = request.get(i).get("body");
            try {
                emailSender.sendEmail(email, subject, content);
            } catch (UnsupportedEncodingException ex) {
                System.out.println("failed to send email : " + ex.getMessage() + email);
            }
        }
        return ResponseEntity.ok("Email Sent Successfully");
    }
    @PostMapping("/sendEmail")
    public ResponseEntity<String> emailSendingSingle(@RequestBody Map<String,String> request) throws MessagingException, UnsupportedEncodingException{

            String email = request.get("email");
            String subject = request.get("subject");
            String content = request.get("body");
            try {
                emailSender.sendEmail(email, subject, content);
            } catch (UnsupportedEncodingException ex) {
                return ResponseEntity.ok("failed to send email : " + ex.getMessage() + email);
            }

        return ResponseEntity.ok("Email Sent Successfully");
    }
}
