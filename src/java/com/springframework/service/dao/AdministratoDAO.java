/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.service.dao;

import com.springframework.model.Administrator;
import com.springframework.model.Mail;
import com.springframework.service.AdministratorService;
import com.springframework.utility.MailService;
import com.springframework.utility.WhatsAppSender;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rizal
 */
@Service("AdministratorService")
@Transactional
public class AdministratoDAO extends GeneralDAO implements AdministratorService {

    @Autowired
    private MailService send;

    @Autowired
    private WhatsAppSender sendWa;

    public AdministratoDAO() {
        super();
    }

    @Override
    public boolean isValid(Administrator model) {
        try {
            StoredProcedureQuery q = em.createStoredProcedureQuery("ProcLogin");
            q.registerStoredProcedureParameter(0, String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            q.setParameter(0, model.getUsername());
            q.setParameter(1, model.getPassword());
            q.execute();

            if (q.getResultList().size() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Administrator> getRole(String user) {
        Query q = em.createQuery("SELECT A FROM Administrator A WHERE A.username = :user");
        q.setParameter("user", user);
        List<Administrator> list = q.getResultList();

        return list;
    }

    @Override
    public boolean isValidAuthentication(String code, String yourcode) {

        return code == null ? yourcode == null : code.equals(yourcode);

    }

    @Override
    public boolean resetPassword(Mail model) {
        try {
            StoredProcedureQuery q = em.createStoredProcedureQuery("ProcResetPassword");
            q.registerStoredProcedureParameter(0, String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            q.setParameter(0, model.getUsername());
            q.setParameter(1, model.getPassword());
            q.execute();

            send.mailService(model);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
