/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.models;

/**
 *
 * @author LENOVO
 */
public class BenhNhanModel {

    private Integer MaBN;
    private String TenBN;
    private boolean GioiTinh = true;
    private String SoDT;
    private String DiaChi;
    private String Email;
    private String TienSuBenh;

    // Constructor
    public BenhNhanModel() {
    }

    public BenhNhanModel(Integer MaBN, String TenBN, String SoDT, String DiaChi, String Email, String TienSuBenh) {
        this.MaBN = MaBN;
        this.TenBN = TenBN;
        this.SoDT = SoDT;
        this.DiaChi = DiaChi;
        this.Email = Email;
        this.TienSuBenh = TienSuBenh;
    }

    //method
    public String toString() {
        return this.TenBN;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BenhNhanModel other = (BenhNhanModel) obj;
        return other.getMaBN().equals(this.getMaBN());
    }

    public int hashCode() {
        return MaBN.hashCode();
    }

    // Getters và Setters
    public Integer getMaBN() {
        return MaBN;
    }

    public void setMaBN(Integer MaBN) {
        this.MaBN = MaBN;
    }

    public String getTenBN() {
        return TenBN;
    }

    public void setTenBN(String TenBN) {
        this.TenBN = TenBN;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String SoDT) {
        this.SoDT = SoDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTienSuBenh() {
        return TienSuBenh;
    }

    public void setTienSuBenh(String TienSuBenh) {
        this.TienSuBenh = TienSuBenh;
    }

}
