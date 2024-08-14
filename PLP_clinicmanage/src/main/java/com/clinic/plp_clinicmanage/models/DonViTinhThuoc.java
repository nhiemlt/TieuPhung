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

    private Integer MaDVT;
    private String TenDVT;

    public DonViTinhThuoc() {
    }

    public DonViTinhThuoc(Integer MaDVT, String TenDVT) {
        this.MaDVT = MaDVT;
        this.TenDVT = TenDVT;
    }

    public Integer getMaDVT() {
        return MaDVT;
    }

    public void setMaDVT(Integer MaDVT) {
        this.MaDVT = MaDVT;
    }

    public String getTenDVT() {
        return TenDVT;
    }

    public void setTenDVT(String TenDVT) {
        this.TenDVT = TenDVT;
    }
}
