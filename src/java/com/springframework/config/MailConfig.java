/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.config;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *
 * @author rizal
 */
@Configuration
@ComponentScan(basePackages = "com.springframework")
public class MailConfig {

    @Bean
    public JavaMailSender getMailSender() {
      final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        
        //Using Gmail SMTP configuration.
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("mailsend82@gmail.com");
        mailSender.setPassword("qwerty123!@#");

        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");
        
        mailSender.setJavaMailProperties(javaMailProperties);
        
        return mailSender;
    }
}
