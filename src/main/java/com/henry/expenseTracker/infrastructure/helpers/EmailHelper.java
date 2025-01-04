package com.henry.expenseTracker.infrastructure.helpers;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class EmailHelper {
    private final JavaMailSender mailSender;

    public void sendEmail(String to, String name){
        MimeMessage message = mailSender.createMimeMessage();
        String htmlContent = this.readHtmlTemplate(name);

        try {
            message.setFrom(new InternetAddress("gusti.paz@gmail.com"));
            message.setRecipients(MimeMessage.RecipientType.TO,to);
            message.setContent(htmlContent, "text/html; charset=utf-8");
        } catch (MessagingException e) {
            log.error("Error sending mail: "+e.getMessage());
        }
    }

    private String readHtmlTemplate(String name) {
        try (var lines = Files.lines(TEMPLATE_PATH)){
            var html = lines.collect(Collectors.joining());
            return html.replace("{name}",name);
        } catch (IOException e){
            log.error("Cant read html file: "+e);
            throw new RuntimeException();
        }

    }

    private final Path TEMPLATE_PATH = Paths.get("src/main/resources/email/email_template.html");
}
