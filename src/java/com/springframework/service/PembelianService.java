/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.service;

import com.springframework.model.Pembelian;
import java.util.List;

/**
 *
 * @author rizal
 */
public interface PembelianService {

    Pembelian getIdBeli();
    
    String generateId(String text);
    
    boolean plusChart(String id, String idb);

    boolean minChart(String id, String idb);

    void clearChart(String id);
    
    void deleteByChart(String id, String idb);
}
