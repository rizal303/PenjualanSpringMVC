/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.service;

import com.springframework.model.WhatsAppClient;

/**
 *
 * @author rizal
 */
public interface WhatsAppService {
    
    void sendMessage(WhatsAppClient client) throws Exception;
}
