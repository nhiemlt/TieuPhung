/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.models;

import java.util.Vector;

/**
 *
 * @author LENOVO
 */
public class ToaThuocModel {

    private Integer MaTT;
    private String MaBN;
    private String TongTien;
    private String NgayXuatHD;
    private String MaND;

    //contructor
    public ToaThuocModel() {
    }

    public ToaThuocModel(Integer MaTT, String MaBN, String TongTien, String NgayXuatHD, String MaND) {
        this.MaTT = MaTT;
        this.MaBN = MaBN;
        this.TongTien = TongTien;
        this.NgayXuatHD = NgayXuatHD;
        this.MaND = MaND;
    }

    //getter và setter
    public Integer getMaTT() {
        return MaTT;
    }

    public void setMaTT(Integer MaTT) {
        this.MaTT = MaTT;
    }

    public String getMaBN() {
        return MaBN;
    }

    public void setMaBN(String MaBN) {
        this.MaBN = MaBN;
    }

    public String getTongTien() {
        return TongTien;
    }

    public void setTongTien(String TongTien) {
        this.TongTien = TongTien;
    }

    public String getNgayXuatHD() {
        return NgayXuatHD;
    }

    public void setNgayXuatHD(String NgayXuatHD) {
        this.NgayXuatHD = NgayXuatHD;
    }

    public String getMaND() {
        return MaND;
    }

    public void setMaND(String MaND) {
        this.MaND = MaND;
    }

    public Vector<?> toObjectArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
