/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.service.dao;

import com.springframework.model.Barang;
import com.springframework.model.KategoriBarang;
import com.springframework.service.BarangInterface;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;

/**
 *
 * @author rizal
 */
@Service("BarangService")
public class BarangDAO extends GeneralDAO implements BarangInterface {

    public BarangDAO() {
        super();
    }

    @Override
    public Barang getById(String id) {

        return em.find(Barang.class, id);
    }

    @Override
    public List<Barang> getAll() {

        Query q = em.createQuery("SELECT B FROM Barang B WHERE B.status = 'Dijual'");
        List<Barang> lb = q.getResultList();

        return lb;
    }

    @Override
    public String generateId() {

        StoredProcedureQuery q = em.createStoredProcedureQuery("ProcGenerateIdBarang");
        q.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
        q.execute();

        return q.getOutputParameterValue(1).toString();
    }

    @Override
    public boolean editById(String id) {
        try {
            StoredProcedureQuery q = em.createStoredProcedureQuery("ProcUpdateStatusBarang");
            q.registerStoredProcedureParameter(0, String.class, ParameterMode.IN);
            q.setParameter(0, id);
            q.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Barang> getByStatusStock(long id) {
        Query q = em.createQuery("SELECT B FROM Barang B WHERE B.status = 'Dijual' AND B.stock > 0 AND B.idKategori.idKategori = :id");
        q.setParameter("id", id);
        List<Barang> lb = q.getResultList();

        return lb;
    }

    @Override
    public int getStockById(String id) {
        try {
            StoredProcedureQuery q = em.createStoredProcedureQuery("ProcGetStockBarang");
            q.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter(2, int.class, ParameterMode.OUT);
            q.setParameter(1, id);
            q.execute();

            return Integer.parseInt(q.getOutputParameterValue(2).toString());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
