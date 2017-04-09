/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.service;

import com.springframework.model.KategoriBarang;
import java.util.List;

/**
 *
 * @author rizal
 */
public interface KategoriBarangService {
  
    boolean editById(long id);
    
    KategoriBarang getById(long id);
    
    List<KategoriBarang> getAll();
}
