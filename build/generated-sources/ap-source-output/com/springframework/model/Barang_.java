package com.springframework.model;

import com.springframework.model.DetailBeli;
import com.springframework.model.KategoriBarang;
import com.springframework.model.Temporary;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-07T22:20:17")
@StaticMetamodel(Barang.class)
public class Barang_ { 

    public static volatile SingularAttribute<Barang, String> barang;
    public static volatile SingularAttribute<Barang, KategoriBarang> idKategori;
    public static volatile SingularAttribute<Barang, String> idBarang;
    public static volatile SingularAttribute<Barang, Double> harga;
    public static volatile ListAttribute<Barang, Temporary> temporaryList;
    public static volatile SingularAttribute<Barang, Integer> stock;
    public static volatile ListAttribute<Barang, DetailBeli> detailBeliList;
    public static volatile SingularAttribute<Barang, String> status;

}