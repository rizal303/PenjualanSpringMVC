/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rizal
 */
@Entity
@Table(name = "detail_beli")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailBeli.findAll", query = "SELECT d FROM DetailBeli d"),
    @NamedQuery(name = "DetailBeli.findByIdDetailBeli", query = "SELECT d FROM DetailBeli d WHERE d.idDetailBeli = :idDetailBeli"),
    @NamedQuery(name = "DetailBeli.findByQty", query = "SELECT d FROM DetailBeli d WHERE d.qty = :qty")})
public class DetailBeli implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_detail_beli")
    private Long idDetailBeli;
    @Basic(optional = false)
    @Column(name = "qty")
    private int qty;
    @JoinColumn(name = "id_barang", referencedColumnName = "id_barang")
    @ManyToOne(optional = false)
    private Barang idBarang;
    @JoinColumn(name = "id_beli", referencedColumnName = "id_beli")
    @ManyToOne(optional = false)
    private Pembelian idBeli;

    public DetailBeli() {
    }

    public DetailBeli(Long idDetailBeli) {
        this.idDetailBeli = idDetailBeli;
    }

    public DetailBeli(Long idDetailBeli, int qty) {
        this.idDetailBeli = idDetailBeli;
        this.qty = qty;
    }

    public Long getIdDetailBeli() {
        return idDetailBeli;
    }

    public void setIdDetailBeli(Long idDetailBeli) {
        this.idDetailBeli = idDetailBeli;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Barang getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(Barang idBarang) {
        this.idBarang = idBarang;
    }

    public Pembelian getIdBeli() {
        return idBeli;
    }

    public void setIdBeli(Pembelian idBeli) {
        this.idBeli = idBeli;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetailBeli != null ? idDetailBeli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailBeli)) {
            return false;
        }
        DetailBeli other = (DetailBeli) object;
        if ((this.idDetailBeli == null && other.idDetailBeli != null) || (this.idDetailBeli != null && !this.idDetailBeli.equals(other.idDetailBeli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.springframework.model.DetailBeli[ idDetailBeli=" + idDetailBeli + " ]";
    }
    
}
