package com.springframework.model;

import com.springframework.model.DetailBeli;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-07T22:20:17")
@StaticMetamodel(Pembelian.class)
public class Pembelian_ { 

    public static volatile SingularAttribute<Pembelian, Double> total;
    public static volatile SingularAttribute<Pembelian, String> idBeli;
    public static volatile ListAttribute<Pembelian, DetailBeli> detailBeliList;
    public static volatile SingularAttribute<Pembelian, Date> tglBeli;

}