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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "temporary")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Temporary.findAll", query = "SELECT t FROM Temporary t"),
    @NamedQuery(name = "Temporary.findById", query = "SELECT t FROM Temporary t WHERE t.id = :id"),
    @NamedQuery(name = "Temporary.findByIdBeli", query = "SELECT t FROM Temporary t WHERE t.idBeli = :idBeli"),
    @NamedQuery(name = "Temporary.findByQty", query = "SELECT t FROM Temporary t WHERE t.qty = :qty")})
public class Temporary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_beli")
    private String idBeli;
    @Basic(optional = false)
    @Column(name = "qty")
    private int qty;
    @JoinColumn(name = "id_barang", referencedColumnName = "id_barang")
    @ManyToOne(optional = false)
    private Barang idBarang;

    public Temporary() {
    }

    public Temporary(Integer id) {
        this.id = id;
    }

    public Temporary(Integer id, String idBeli, int qty) {
        this.id = id;
        this.idBeli = idBeli;
        this.qty = qty;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdBeli() {
        return idBeli;
    }

    public void setIdBeli(String idBeli) {
        this.idBeli = idBeli;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Temporary)) {
            return false;
        }
        Temporary other = (Temporary) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.springframework.model.Temporary[ id=" + id + " ]";
    }
    
}
