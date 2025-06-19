package com.EMAIL.demo.Component;

import com.EMAIL.demo.Models.Response;
import com.EMAIL.demo.Repositories.Background;
import com.EMAIL.demo.services.EmailSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.*;

@Component
public class EmailScheduler {
    @Autowired
    private Background background;
    @Autowired
    private EmailSender emailSender;
    private static final Logger log = LoggerFactory.getLogger(EmailScheduler.class);
    private static final SimpleDateFormat simple = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws JsonProcessingException, MessagingException, UnsupportedEncodingException {
        boolean responses = background.tableExists();
        List<Response> responseList = background.findAll();

        Optional<Response> response = background.findById(UUID.fromString("c6c89261-34f8-4302-aef7-f025b6f16b76"));
        log.info("responses {}", response);
        log.info("responses list {}", responseList.toString());
        List<Map<String, String>> emails = new ArrayList<>();
        for (Response value : responseList) {

            log.info("responses {}", value.getCreatedAt().getHour() + " " + value.getCreatedAt().getSecond());
            OffsetDateTime storedDate = value.getCreatedAt();

            OffsetDateTime now = OffsetDateTime.now();

            Duration duration = Duration.between(storedDate, now);

            long milliseconds = duration.toMillis();
            log.info("milliseconds {}", milliseconds);

            if (milliseconds < 5000 && value.getResponseStatus().equals("SUCCESS")) {
                String email = value.getResponseData();
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map = objectMapper.readValue(email, Map.class);
                Map<String, String> struct = new HashMap<>();
                struct.put("email", map.get("email").toString());
                struct.put("subject", "Test from background service");
                struct.put("Content", "<h1>Hello there click below link for hackverse</h1><br /><a href='https://hackverse.knowvationlearnings.in'>Hackverse</a>");
                log.info("Content set {}", map.get("email"));

                emails.add(struct);


                log.info("sending emails {}", emails);
                emailSender.emailSendingGroup(emails);
                log.info("emails sent {}", "process finished");
            } else {
                log.info("Email already sent");
            }
        }
    }
}
