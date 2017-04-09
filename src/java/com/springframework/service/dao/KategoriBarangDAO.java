/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.service.dao;

import com.springframework.model.Administrator;
import com.springframework.model.KategoriBarang;
import com.springframework.service.KategoriBarangService;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author amikom
 */
@Service("KategoriBarangService")
@Transactional
public class KategoriBarangDAO extends GeneralDAO implements KategoriBarangService {

    public KategoriBarangDAO() {
        super();
    }

    @Override
    public List<KategoriBarang> getAll() {
        Query q = em.createQuery("SELECT K FROM KategoriBarang K WHERE K.status = 'Aktif'");
        List<KategoriBarang> list = q.getResultList();

        return list;
    }

    @Override
    public KategoriBarang getById(long id) {
        KategoriBarang kb = em.find(KategoriBarang.class, id);

        return kb;
    }

    @Override
    public boolean editById(long id) {
        try {
            StoredProcedureQuery q = em.createStoredProcedureQuery("ProcUpdateStatusKategori");
            q.registerStoredProcedureParameter(0, long.class, ParameterMode.IN);
            q.setParameter(0, id);
            q.execute();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
