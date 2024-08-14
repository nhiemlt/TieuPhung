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

<<<<<<< HEAD
    private Integer MaThuoc;
=======
    private int MaThuoc;
>>>>>>> 2c32de03eb62ec8eab0b5230a3c2b30b39fb83b4
    private String TenThuoc;
    private String CongDung;
    private String HDLieuLuong;
    private float GiaTien;
    private int SoLuong;
    private Date HanSD;
    private int MaDVT;

    //contructors
    public ThuocModel() {
    }

<<<<<<< HEAD
    public ThuocModel(Integer MaThuoc, String TenThuoc, String CongDung, String HDLieuLuong, String GiaTien, int SoLuong, Date HanSD, int MaDVT) {
=======
    public ThuocModel(int MaThuoc, String TenThuoc, String CongDung, String HDLieuLuong, float GiaTien, int SoLuong, Date HanSD, String MaDVT) {
>>>>>>> 2c32de03eb62ec8eab0b5230a3c2b30b39fb83b4
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
<<<<<<< HEAD
    public Integer getMaThuoc() {
        return MaThuoc;
    }

    public void setMaThuoc(Integer MaThuoc) {
=======
    public int getMaThuoc() {
        return MaThuoc;
    }

    public void setMaThuoc(int MaThuoc) {
>>>>>>> 2c32de03eb62ec8eab0b5230a3c2b30b39fb83b4
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

    public float  getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(float  GiaTien) {
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

    public int getMaDVT() {
        return MaDVT;
    }

    public void setMaDVT(int MaDVT) {
        this.MaDVT = MaDVT;
    }

}
