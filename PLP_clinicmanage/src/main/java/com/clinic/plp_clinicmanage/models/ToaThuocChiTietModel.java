/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.models;

/**
 *
 * @author LENOVO
 */
public class ToaThuocChiTietModel {

    private int MaTTCT;
    private int MaTT;
    private int MaBN;
    private int MaND;
    private int MaThuoc;
    private int SoLuong;
    private float  GiaBan;
    private float  ThanhTien;

    //contructor
    public ToaThuocChiTietModel() {
    }

    public ToaThuocChiTietModel(int MaTTCT, int MaTT, int MaBN, int MaND, int MaThuoc, int SoLuong, float  GiaBan, float  ThanhTien) {
        this.MaTTCT = MaTTCT;
        this.MaTT = MaTT;
        this.MaBN = MaBN;
        this.MaND = MaND;
        this.MaThuoc = MaThuoc;
        this.SoLuong = SoLuong;
        this.GiaBan = GiaBan;
        this.ThanhTien = ThanhTien;
    }

    public int getMaTTCT() {
        return MaTTCT;
    }

    public void setMaTTCT(int MaTTCT) {
        this.MaTTCT = MaTTCT;
    }

    public int getMaTT() {
        return MaTT;
    }

    public void setMaTT(int MaTT) {
        this.MaTT = MaTT;
    }

    public int getMaBN() {
        return MaBN;
    }

    public void setMaBN(int MaBN) {
        this.MaBN = MaBN;
    }

    public int getMaND() {
        return MaND;
    }

    public void setMaND(int MaND) {
        this.MaND = MaND;
    }

    public int getMaThuoc() {
        return MaThuoc;
    }

    public void setMaThuoc(int MaThuoc) {
        this.MaThuoc = MaThuoc;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public float getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(float GiaBan) {
        this.GiaBan = GiaBan;
    }

    public float getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(float ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    // getter và setter
}