/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springframework.model;

/**
 *
 * @author rizal
 */
public class Chart {
    
    private String idBeli;
    private String idBarang;
    private String barang;
    private double harga;
    private int qty;
    private double amount;

    /**
     * @return the idBeli
     */
    public String getIdBeli() {
        return idBeli;
    }

    /**
     * @param idBeli the idBeli to set
     */
    public void setIdBeli(String idBeli) {
        this.idBeli = idBeli;
    }

    /**
     * @return the idBarang
     */
    public String getIdBarang() {
        return idBarang;
    }

    /**
     * @param idBarang the idBarang to set
     */
    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }

    /**
     * @return the barang
     */
    public String getBarang() {
        return barang;
    }

    /**
     * @param barang the barang to set
     */
    public void setBarang(String barang) {
        this.barang = barang;
    }

    /**
     * @return the harga
     */
    public double getHarga() {
        return harga;
    }

    /**
     * @param harga the harga to set
     */
    public void setHarga(double harga) {
        this.harga = harga;
    }

    /**
     * @return the qty
     */
    public int getQty() {
        return qty;
    }

    /**
     * @param qty the qty to set
     */
    public void setQty(int qty) {
        this.qty = qty;
    }

    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount() {
        this.amount = getHarga()*getQty();
    }
    
}
