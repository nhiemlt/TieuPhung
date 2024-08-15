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

    private Integer MaTTCT;
    private Integer MaTT;
    private Integer MaBN;
    private Integer MaND;
    private Integer MaThuoc;
    private int SoLuong;
    private double GiaBan;
    private double ThanhTien;

    public ToaThuocChiTietModel() {
    }

    public ToaThuocChiTietModel(Integer MaTTCT, Integer MaTT, Integer MaBN, Integer MaND, Integer MaThuoc, int SoLuong, double GiaBan, double ThanhTien) {
        this.MaTTCT = MaTTCT;
        this.MaTT = MaTT;
        this.MaBN = MaBN;
        this.MaND = MaND;
        this.MaThuoc = MaThuoc;
        this.SoLuong = SoLuong;
        this.GiaBan = GiaBan;
        this.ThanhTien = ThanhTien;
    }

    public Integer getMaTTCT() {
        return MaTTCT;
    }

    public void setMaTTCT(Integer MaTTCT) {
        this.MaTTCT = MaTTCT;
    }

    public Integer getMaTT() {
        return MaTT;
    }

    public void setMaTT(Integer MaTT) {
        this.MaTT = MaTT;
    }

    public Integer getMaBN() {
        return MaBN;
    }

    public void setMaBN(Integer MaBN) {
        this.MaBN = MaBN;
    }

    public Integer getMaND() {
        return MaND;
    }

    public void setMaND(Integer MaND) {
        this.MaND = MaND;
    }

    public Integer getMaThuoc() {
        return MaThuoc;
    }

    public void setMaThuoc(Integer MaThuoc) {
        this.MaThuoc = MaThuoc;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(double GiaBan) {
        this.GiaBan = GiaBan;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
}
