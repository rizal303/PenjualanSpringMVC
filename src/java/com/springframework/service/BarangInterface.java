/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.service;

import com.springframework.model.Barang;
import com.springframework.model.KategoriBarang;
import java.util.List;

/**
 *
 * @author rizal
 */
public interface BarangInterface {

    String generateId();
    
    boolean editById(String id);
    
    int getStockById(String id);
    
    Barang getById(String id);
    
    List<Barang> getAll();
    
    List<Barang> getByStatusStock(long id);
    
    
}
