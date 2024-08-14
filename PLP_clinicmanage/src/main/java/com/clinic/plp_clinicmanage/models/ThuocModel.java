/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.models;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class ThuocModel {

    private Integer MaThuoc;
    private String TenThuoc;
    private String CongDung;
    private String HDLieuLuong;
    private String GiaTien;
    private int SoLuong;
    private Date HanSD;
    private String MaDVT;

    //contructors
    public ThuocModel() {
    }

    public ThuocModel(Integer MaThuoc, String TenThuoc, String CongDung, String HDLieuLuong, String GiaTien, int SoLuong, Date HanSD, String MaDVT) {
        this.MaThuoc = MaThuoc;
        this.TenThuoc = TenThuoc;
        this.CongDung = CongDung;
        this.HDLieuLuong = HDLieuLuong;
        this.GiaTien = GiaTien;
        this.SoLuong = SoLuong;
        this.HanSD = HanSD;
        this.MaDVT = MaDVT;
    }

    // getter và setter
    public Integer getMaThuoc() {
        return MaThuoc;
    }

    public void setMaThuoc(Integer MaThuoc) {
        this.MaThuoc = MaThuoc;
    }

    public String getTenThuoc() {
        return TenThuoc;
    }

    public void setTenThuoc(String TenThuoc) {
        this.TenThuoc = TenThuoc;
    }

    public String getCongDung() {
        return CongDung;
    }

    public void setCongDung(String CongDung) {
        this.CongDung = CongDung;
    }

    public String getHDLieuLuong() {
        return HDLieuLuong;
    }

    public void setHDLieuLuong(String HDLieuLuong) {
        this.HDLieuLuong = HDLieuLuong;
    }

    public String getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(String GiaTien) {
        this.GiaTien = GiaTien;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public Date getHanSD() {
        return HanSD;
    }

    public void setHanSD(Date HanSD) {
        this.HanSD = HanSD;
    }

    public String getMaDVT() {
        return MaDVT;
    }

    public void setMaDVT(String MaDVT) {
        this.MaDVT = MaDVT;
    }

}
