/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.models;

/**
 *
 * @author LENOVO
 */
public class DonViTinhThuoc {

    private String MaDVT;
    private String TenDVT;

    public DonViTinhThuoc() {
    }

    public DonViTinhThuoc(String MaDVT, String TenDVT) {
        this.MaDVT = MaDVT;
        this.TenDVT = TenDVT;
    }

    public String getMaDVT() {
        return MaDVT;
    }

    public void setMaDVT(String MaDVT) {
        this.MaDVT = MaDVT;
    }

    public String getTenDVT() {
        return TenDVT;
    }

    public void setTenDVT(String TenDVT) {
        this.TenDVT = TenDVT;
    }
}
