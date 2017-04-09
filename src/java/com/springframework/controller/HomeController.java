/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.controller;

import com.springframework.config.MailConfig;
import com.springframework.model.*;
import com.springframework.service.dao.AdministratoDAO;
import com.springframework.service.dao.TemporaryDAO;
import com.springframework.utility.CodeGenerator;
import com.springframework.utility.WhatsAppSender;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

/**
 *
 * @author rizal
 */
@Controller
public class HomeController {

    @Autowired
    private AdministratoDAO service;

    @Autowired
    private WhatsAppSender send;

    @RequestMapping(value = "index.htm")
    public String viewIndex() {
        return "index";
    }

    @RequestMapping(value = "forgotpassword.htm")
    public String viewForgotPassword() {
        return "forgotpassword";
    }

    @RequestMapping(value = "errorsendmail.htm")
    public String viewError() {
        return "errorsendmail";
    }

    @RequestMapping(value = "accesdenied.htm")
    public String viewAccesDenied() {
        return "accesdenied";
    }

    @RequestMapping(value = "proccesLogin.htm", method = RequestMethod.POST)
    public void login(HttpServletResponse response, HttpSession session,
            @ModelAttribute("Administrator") Administrator a) throws IOException, Exception {

        if (service.isValid(a)) {

            List<Administrator> list = service.getRole(a.getUsername());
            for (Administrator adm : list) {
                if (adm.getRole().equals("administrator")) {

                    CodeGenerator passwordGenerator = new CodeGenerator.CodeGeneratorBuilder()
                            .useDigits(true)
                            .build();
                    String code = passwordGenerator.generate(6);

                    WhatsAppClient wa = new WhatsAppClient();
                    wa.setNumber(adm.getPhone());
                    wa.setMessage(code + " is your InventoryApps authentication code");

                    send.sendMessage(wa);

                    response.sendRedirect("authenticationlogin.htm");

                    session.setAttribute("code", code);
                    session.setAttribute("tempuser", a.getUsername());
                    session.setAttribute("role", adm.getRole());
                } else if (adm.getRole().equals("admin")) {
                    
                    response.sendRedirect("dashboardadmin.htm");

                    session.setAttribute("idebeli", "");
                    session.setAttribute("username", a.getUsername());
                    session.setAttribute("role", adm.getRole());
                }
            }
        } else {

            response.sendRedirect("index.htm");
        }
    }

    @RequestMapping(value = "authenticationlogin.htm")
    public String viewValidAuthentication() {
        return "authenticationlogin";
    }

    @RequestMapping(value = "authentication.htm", method = RequestMethod.POST)
    public void authLogin(HttpServletResponse response, HttpSession session,
            @RequestParam(value = "number", required = false) String codeyour) throws IOException {

        if (service.isValidAuthentication(session.getAttribute("code").toString(), codeyour)) {

            session.setAttribute("username", session.getAttribute("tempuser"));
            response.sendRedirect("dashboardadministrator.htm");
        } else {
            response.sendRedirect("authenticationlogin.htm");
        }
    }

    @RequestMapping(value = "resetpassword.htm", method = RequestMethod.POST)
    public void resetPassword(HttpServletResponse response,
            @ModelAttribute("Mail") Mail m) throws IOException {

        CodeGenerator passwordGenerator = new CodeGenerator.CodeGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        String newPassword = passwordGenerator.generate(8);

        Mail model = new Mail();

        model.setTo(m.getTo());
        model.setSubject("Reset Your Password");
        model.setMessage("Use this new password to login " + "" + newPassword + "" + " and immediately make the change");

        model.setPassword(newPassword);
        model.setUsername(m.getUsername());

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MailConfig.class);
        if (service.resetPassword(model)) {
            response.sendRedirect("index.htm");
        } else {
            response.sendRedirect("errorsendmail.htm");
        }
        ((AbstractApplicationContext) context).close();
    }

    @RequestMapping(value = "logout.htm")
    public void logout(HttpServletResponse response, HttpSession session, SessionStatus status) throws IOException {

        status.setComplete();
        session.invalidate();

        response.sendRedirect("index.htm");
    }

}
