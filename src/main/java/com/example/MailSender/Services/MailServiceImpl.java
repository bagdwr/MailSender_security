package com.example.MailSender.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String admin_email;

    @Override
    public void sendMail(String email,String text) {
        try {
            SimpleMailMessage mail=new SimpleMailMessage();
            mail.setFrom(admin_email);
            mail.setTo(email);
            mail.setText(text);
            mail.setSubject("Login");
            javaMailSender.send(mail);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
