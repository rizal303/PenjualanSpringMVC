/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rizal
 */
@Entity
@Table(name = "barang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Barang.findAll", query = "SELECT b FROM Barang b"),
    @NamedQuery(name = "Barang.findByIdBarang", query = "SELECT b FROM Barang b WHERE b.idBarang = :idBarang"),
    @NamedQuery(name = "Barang.findByBarang", query = "SELECT b FROM Barang b WHERE b.barang = :barang"),
    @NamedQuery(name = "Barang.findByHarga", query = "SELECT b FROM Barang b WHERE b.harga = :harga"),
    @NamedQuery(name = "Barang.findByStock", query = "SELECT b FROM Barang b WHERE b.stock = :stock"),
    @NamedQuery(name = "Barang.findByStatus", query = "SELECT b FROM Barang b WHERE b.status = :status")})
public class Barang implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_barang")
    private String idBarang;
    @Column(name = "barang")
    private String barang;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "harga")
    private Double harga;
    @Column(name = "stock")
    private Integer stock;
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "id_kategori", referencedColumnName = "id_kategori")
    @ManyToOne(optional = false)
    private KategoriBarang idKategori;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBarang")
    private List<Temporary> temporaryList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBarang")
    private List<DetailBeli> detailBeliList;

    public Barang() {
    }

    public Barang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public KategoriBarang getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(KategoriBarang idKategori) {
        this.idKategori = idKategori;
    }

    @XmlTransient
    public List<Temporary> getTemporaryList() {
        return temporaryList;
    }

    public void setTemporaryList(List<Temporary> temporaryList) {
        this.temporaryList = temporaryList;
    }

    @XmlTransient
    public List<DetailBeli> getDetailBeliList() {
        return detailBeliList;
    }

    public void setDetailBeliList(List<DetailBeli> detailBeliList) {
        this.detailBeliList = detailBeliList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBarang != null ? idBarang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Barang)) {
            return false;
        }
        Barang other = (Barang) object;
        if ((this.idBarang == null && other.idBarang != null) || (this.idBarang != null && !this.idBarang.equals(other.idBarang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.springframework.model.Barang[ idBarang=" + idBarang + " ]";
    }
    
}
