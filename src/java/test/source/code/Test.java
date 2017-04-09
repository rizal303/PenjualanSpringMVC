package test.source.code;

import com.springframework.model.WhatsAppClient;
import com.springframework.service.dao.AdministratoDAO;
import com.springframework.service.dao.PembelianDAO;
import com.springframework.utility.CodeGenerator;
import com.springframework.utility.WhatsAppSender;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rizal
 */
public class Test {

    public static void main(String[] args) throws Exception {

        PembelianDAO b = new PembelianDAO();
        if(b.getIdBeli() != null){
            System.out.println("PEMBELIAN");
        }else{
            System.out.println("TEMPORARY");
        }
    }

}
