/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.service.dao;

import com.springframework.service.GeneralService;
import javax.persistence.*;

/**
 *
 * @author rizal
 */
public class GeneralDAO implements GeneralService {

    protected EntityManager em;

    public GeneralDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("InventoryApplicationPU");
        em = emf.createEntityManager();
    }

    @Override
    public boolean save(Object O) {
        try {
            em.getTransaction().begin();
            em.persist(O);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            return false;
        }
    }

    @Override
    public boolean edit(Object O) {
        try {
            em.getTransaction().begin();
            em.merge(O);
            em.getTransaction().commit();
            
            return true;

        } catch (Exception ex) {
            em.getTransaction().rollback();
            
            return false;
        }
    }

    @Override
    public boolean delete(Object O) {
        try {
            em.getTransaction().begin();
            em.remove(O);
            em.getTransaction().commit();
            
            return true;

        } catch (Exception ex) {
            em.getTransaction().rollback();
            
            return false;
        }
    }

}
