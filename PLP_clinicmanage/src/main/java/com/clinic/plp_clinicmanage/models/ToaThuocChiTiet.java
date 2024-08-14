/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.models;

/**
 *
 * @author LENOVO
 */
public class ToaThuocChiTiet {

    private String MaTTCT;
    private String MaTT;
    private String MaBN;
    private String MaND;
    private String MaThuoc;
    private int SoLuong;
    private String GiaBan;
    private String ThanhTien;

    //contructor
    public ToaThuocChiTiet() {
    }

    public ToaThuocChiTiet(String MaTTCT, String MaTT, String MaBN, String MaND, String MaThuoc, int SoLuong, String GiaBan, String ThanhTien) {
        this.MaTTCT = MaTTCT;
        this.MaTT = MaTT;
        this.MaBN = MaBN;
        this.MaND = MaND;
        this.MaThuoc = MaThuoc;
        this.SoLuong = SoLuong;
        this.GiaBan = GiaBan;
        this.ThanhTien = ThanhTien;
    }

    // getter và setter
    public String getMaTTCT() {
        return MaTTCT;
    }

    public void setMaTTCT(String MaTTCT) {
        this.MaTTCT = MaTTCT;
    }

    public String getMaTT() {
        return MaTT;
    }

    public void setMaTT(String MaTT) {
        this.MaTT = MaTT;
    }

    public String getMaBN() {
        return MaBN;
    }

    public void setMaBN(String MaBN) {
        this.MaBN = MaBN;
    }

    public String getMaND() {
        return MaND;
    }

    public void setMaND(String MaND) {
        this.MaND = MaND;
    }

    public String getMaThuoc() {
        return MaThuoc;
    }

    public void setMaThuoc(String MaThuoc) {
        this.MaThuoc = MaThuoc;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public String getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(String GiaBan) {
        this.GiaBan = GiaBan;
    }

    public String getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(String ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

}
