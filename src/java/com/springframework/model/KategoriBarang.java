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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "kategori_barang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KategoriBarang.findAll", query = "SELECT k FROM KategoriBarang k"),
    @NamedQuery(name = "KategoriBarang.findByIdKategori", query = "SELECT k FROM KategoriBarang k WHERE k.idKategori = :idKategori"),
    @NamedQuery(name = "KategoriBarang.findByKategori", query = "SELECT k FROM KategoriBarang k WHERE k.kategori = :kategori"),
    @NamedQuery(name = "KategoriBarang.findByStatus", query = "SELECT k FROM KategoriBarang k WHERE k.status = :status")})
public class KategoriBarang implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_kategori")
    private Long idKategori;
    @Column(name = "kategori")
    private String kategori;
    @Column(name = "Status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idKategori")
    private List<Barang> barangList;

    public KategoriBarang() {
    }

    public KategoriBarang(Long idKategori) {
        this.idKategori = idKategori;
    }

    public Long getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(Long idKategori) {
        this.idKategori = idKategori;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<Barang> getBarangList() {
        return barangList;
    }

    public void setBarangList(List<Barang> barangList) {
        this.barangList = barangList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKategori != null ? idKategori.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KategoriBarang)) {
            return false;
        }
        KategoriBarang other = (KategoriBarang) object;
        if ((this.idKategori == null && other.idKategori != null) || (this.idKategori != null && !this.idKategori.equals(other.idKategori))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.springframework.model.KategoriBarang[ idKategori=" + idKategori + " ]";
    }
    
}
