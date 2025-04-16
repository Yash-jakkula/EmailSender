package com.EMAIL.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.UnsupportedEncodingException;

@SpringBootApplication
@EnableAsync
public class EmailServiceApplication {
//	@Autowired
// private EmailSender emailSender;

	public static void main(String[] args) {

		SpringApplication.run(EmailServiceApplication.class, args);

    }
//@EventListener(ApplicationReadyEvent.class)
//	public void sendMail(){
//		JavaMailSender mailSender = new JavaMailSenderImpl();
//		String receipientEmail = "21eg105h21@anurag.edu.in";
//		String subject = "Hello from Yashwanth testing";
//		String content = "<p>Hello,</p><p>This is a test mail from yashwanth with spring and java</P>";
//		try {
//			emailSender.sendEmail(receipientEmail,subject,content);
//		} catch (UnsupportedEncodingException e) {
//			throw new RuntimeException(e);
//		}
//	}

}
