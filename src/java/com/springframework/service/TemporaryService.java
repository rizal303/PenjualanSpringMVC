/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.service;

import com.springframework.model.Temporary;
import java.util.List;

/**
 *
 * @author amikom
 */
public interface TemporaryService {
   
    List<Temporary> getChartById(String id);

    List<Temporary> getCount(String id);
    
    double  subTotalByChart(String idbeli);
}
