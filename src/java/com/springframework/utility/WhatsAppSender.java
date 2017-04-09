/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.utility;

import com.springframework.model.WhatsAppClient;
import com.springframework.service.WhatsAppService;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.stereotype.Service;

/**
 *
 * @author rizal
 */
@Service("WhatsAppService")
public class WhatsAppSender implements WhatsAppService {

    private static final String CLIENT_ID = "TRIAL_ACCOUNT";
    private static final String CLIENT_SECRET = "PUBLIC_SECRET";
    private static final String WA_GATEWAY_URL = "http://api.whatsmate.net/v1/whatsapp/single/message/2";

    @Override
    public void sendMessage(WhatsAppClient client) throws Exception {
        String jsonPayload = new StringBuilder()
                .append("{")
                .append("\"number\":\"")
                .append(client.getNumber())
                .append("\",")
                .append("\"message\":\"")
                .append(client.getMessage())
                .append("\"")
                .append("}")
                .toString();

        URL url = new URL(WA_GATEWAY_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
        conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(jsonPayload.getBytes());
        os.flush();
        os.close();

        int statusCode = conn.getResponseCode();
        System.out.println("Response from WA Gateway: \n");
        System.out.println("Status Code: " + statusCode);
        BufferedReader br = new BufferedReader(new InputStreamReader(
                (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
        ));
        String output;
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }
        conn.disconnect();
    }

}
