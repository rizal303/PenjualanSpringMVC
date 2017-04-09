/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.service.dao;

import com.springframework.model.Pembelian;
import com.springframework.service.PembelianService;
import java.util.Calendar;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author rizal
 */
@Service("PembelianService")
@Transactional
public class PembelianDAO extends GeneralDAO implements PembelianService {

    public PembelianDAO() {
        super();
    }

    @Override
    public String generateId(String text) {
        String id, tgl;
        StringBuilder nol = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        if ((cal.get(Calendar.MONTH) + 1) < 10) {
            tgl = cal.get(Calendar.YEAR) + ".0" + (cal.get(Calendar.MONTH) + 1) + ".";
        } else {
            tgl = cal.get(Calendar.YEAR) + "." + (cal.get(Calendar.MONTH) + 1) + ".";
        }
        if (text.equalsIgnoreCase("Temporary")) {
            try {
                StoredProcedureQuery q = em.createStoredProcedureQuery("ProcGenerateIdTransaksi");
                q.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
                q.execute();

                id = String.valueOf(Integer.parseInt(q.getOutputParameterValue(1).toString()) + 1);
                nol.setLength(0);
                for (int i = id.length(); i < 4; i++) {
                    nol.append("0");
                }

                nol.append(id);
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
            }
        } else if (text.equalsIgnoreCase("Pembelian")) {
            try {
                StoredProcedureQuery q = em.createStoredProcedureQuery("ProcGenereteIdPembelian");
                q.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
                q.execute();

                id = String.valueOf(Integer.parseInt(q.getOutputParameterValue(1).toString()) + 1);
                nol.setLength(0);
                for (int i = id.length(); i < 4; i++) {
                    nol.append("0");
                }

                nol.append(id);
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
            }
        }

        //PJ2015.01.0001
        return "PJ" + tgl + nol;
    }

    @Override
    public boolean plusChart(String id, String idb) {
        try {
            StoredProcedureQuery q = em.createStoredProcedureQuery("ProcPlusChart");
            q.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            q.setParameter(1, id);
            q.setParameter(2, idb);
            q.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();

            return false;
        }
    }

    @Override
    public boolean minChart(String id, String idb) {
        try {
            StoredProcedureQuery q = em.createStoredProcedureQuery("ProcMinChart");
            q.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            q.setParameter(1, id);
            q.setParameter(2, idb);
            q.execute();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();

            return false;
        }
    }

    @Override
    public Pembelian getIdBeli() {
        try {
            StoredProcedureQuery q = em.createStoredProcedureQuery("ProcGetIdPembelian");
            q.registerStoredProcedureParameter(1, String.class, ParameterMode.OUT);
            q.execute();

            Pembelian p = new Pembelian();
            p.setIdBeli(q.getOutputParameterValue(1).toString());
            
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();

            return null;
        }
    }

    @Override
    public void clearChart(String id) {
        try {
            StoredProcedureQuery q = em.createStoredProcedureQuery("ProcDeleteTempById");
            q.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            q.setParameter(1, id);
            q.execute();

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();

        }
    }

    @Override
    public void deleteByChart(String id, String idb) {
        try {
            StoredProcedureQuery q = em.createStoredProcedureQuery("ProcDeleteBarangByChart");
            q.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
            q.setParameter(1, id);
            q.setParameter(2, idb);
            q.execute();

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();

        }
    }
}
