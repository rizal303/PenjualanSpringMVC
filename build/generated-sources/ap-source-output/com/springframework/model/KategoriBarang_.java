package com.springframework.model;

import com.springframework.model.Barang;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-04-07T22:20:17")
@StaticMetamodel(KategoriBarang.class)
public class KategoriBarang_ { 

    public static volatile SingularAttribute<KategoriBarang, Long> idKategori;
    public static volatile ListAttribute<KategoriBarang, Barang> barangList;
    public static volatile SingularAttribute<KategoriBarang, String> kategori;
    public static volatile SingularAttribute<KategoriBarang, String> status;

}