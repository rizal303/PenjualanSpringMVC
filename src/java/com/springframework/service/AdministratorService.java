/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.service;

import com.springframework.model.Administrator;
import com.springframework.model.Mail;
import com.springframework.model.WhatsAppClient;
import java.util.List;

/**
 *
 * @author rizal
 */
public interface AdministratorService {

    boolean isValid(Administrator model);

    boolean resetPassword(Mail model);

    boolean isValidAuthentication(String code, String yourcode);

    List<Administrator> getRole(String user);
}
