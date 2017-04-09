/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.service.dao;

import com.springframework.model.Temporary;
import com.springframework.service.TemporaryService;
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
@Service("TemporaryService")
@Transactional
public class TemporaryDAO extends GeneralDAO implements TemporaryService {

    @Override
    public List<Temporary> getCount(String id) {
        Query q = em.createQuery("SELECT COUNT(T.idBarang) AS jml FROM Temporary T WHERE T.idBeli = :idbeli");
        q.setParameter("idbeli", id);

        List<Temporary> list = q.getResultList();

        return list;
    }

    @Override
    public List<Temporary> getChartById(String id) {
        Query q = em.createQuery("SELECT T FROM Temporary T WHERE T.idBeli = :idbeli");
//        Query q = em.createNativeQuery("SELECT id_beli, id_barang, qty FROM temporary");
        q.setParameter("idbeli", id);

        List<Temporary> list = q.getResultList();

        return list;
    }

    @Override
    public double subTotalByChart(String idbeli) {
        try {
            StoredProcedureQuery q = em.createStoredProcedureQuery("ProcGetSubByChartId");
            q.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter(2, double.class, ParameterMode.OUT);
            q.setParameter(1, idbeli);
            q.execute();

            return Double.valueOf(q.getOutputParameterValue(2).toString());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
