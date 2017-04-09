package com.springframework.model;

import com.springframework.model.Barang;
import com.springframework.model.Pembelian;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-07T22:20:17")
@StaticMetamodel(DetailBeli.class)
public class DetailBeli_ { 

    public static volatile SingularAttribute<DetailBeli, Barang> idBarang;
    public static volatile SingularAttribute<DetailBeli, Pembelian> idBeli;
    public static volatile SingularAttribute<DetailBeli, Integer> qty;
    public static volatile SingularAttribute<DetailBeli, Long> idDetailBeli;

}