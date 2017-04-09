/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.utility;

import com.springframework.model.Mail;
import java.util.Date;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
;
import org.springframework.stereotype.Service;

/**
 *
 * @author rizal
 */
@Service("MailService")
public class SendMail implements MailService {

    @Autowired
    JavaMailSender mailSender;

    @Override
    public boolean mailService(Object object) {

        Mail model = (Mail) object;

        MimeMessagePreparator preparator = getMessagePreparator(model);

        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Succes guys :)");

            return true;
        } catch (MailException ex) {
            System.err.println(ex.getMessage());

            return false;
        }
    }

    private MimeMessagePreparator getMessagePreparator(final Mail model) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mm) throws Exception {
                mm.setFrom(new InternetAddress("mailsend82@gmail.com"));
                mm.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(model.getTo()));
                mm.setSubject(model.getSubject());
                mm.setSentDate(new Date());
                mm.setText(model.getMessage());
                
            }
        };
        return preparator;
    }
}
