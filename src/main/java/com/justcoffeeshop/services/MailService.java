package com.justcoffeeshop.services;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.justcoffeeshop.models.UserModel;

/**
 * Service for sending emails.
 * <p>
 * We use the @Async annotation to send emails asynchronously.
 */
@Service
public class MailService {

    private JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendMail(UserModel user) throws MailException {
        // send email
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(user.getEmail());
        mail.setFrom("smthanh@gmail.com");
        mail.setSubject("Welcome to My Website");
        mail.setText("Hello, " + user.getFirstName() + " This is a cool email notificaiton");
        javaMailSender.send(mail);
    }
}
