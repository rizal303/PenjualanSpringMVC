/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rizal
 */
@Entity
@Table(name = "pembelian")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pembelian.findAll", query = "SELECT p FROM Pembelian p"),
    @NamedQuery(name = "Pembelian.findByIdBeli", query = "SELECT p FROM Pembelian p WHERE p.idBeli = :idBeli"),
    @NamedQuery(name = "Pembelian.findByTglBeli", query = "SELECT p FROM Pembelian p WHERE p.tglBeli = :tglBeli"),
    @NamedQuery(name = "Pembelian.findByTotal", query = "SELECT p FROM Pembelian p WHERE p.total = :total")})
public class Pembelian implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_beli")
    private String idBeli;
    @Basic(optional = false)
    @Column(name = "tgl_beli")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tglBeli;
    @Basic(optional = false)
    @Column(name = "total")
    private double total;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBeli")
    private List<DetailBeli> detailBeliList;

    public Pembelian() {
    }

    public Pembelian(String idBeli) {
        this.idBeli = idBeli;
    }

    public Pembelian(String idBeli, Date tglBeli, double total) {
        this.idBeli = idBeli;
        this.tglBeli = tglBeli;
        this.total = total;
    }

    public String getIdBeli() {
        return idBeli;
    }

    public void setIdBeli(String idBeli) {
        this.idBeli = idBeli;
    }

    public Date getTglBeli() {
        return tglBeli;
    }

    public void setTglBeli(Date tglBeli) {
        this.tglBeli = tglBeli;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
        hash += (idBeli != null ? idBeli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pembelian)) {
            return false;
        }
        Pembelian other = (Pembelian) object;
        if ((this.idBeli == null && other.idBeli != null) || (this.idBeli != null && !this.idBeli.equals(other.idBeli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.springframework.model.Pembelian[ idBeli=" + idBeli + " ]";
    }
    
}
