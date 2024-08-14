/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.models;

import java.util.Objects;
import java.util.Vector;

/**
 *
 * @author LENOVO
 */
public class NguoiDung {

    private Integer MaND;
    private String TenND;
    private String TenTK;
    private String MatKhau;
    private boolean GioiTinh;
    private int VaiTro;
    private String Email;
    private String ChucVu;
    private String SDT;
    private String HinhAnh;

    // Constructor
    public NguoiDung() {
    }

    public NguoiDung(Integer MaND, String TenND, String TenTK, String MatKhau, boolean GioiTinh, int VaiTro, String Email, String ChucVu, String SDT, String HinhAnh) {
        this.MaND = MaND;
        this.TenND = TenND;
        this.TenTK = TenTK;
        this.MatKhau = MatKhau;
        this.GioiTinh = GioiTinh;
        this.VaiTro = VaiTro;
        this.Email = Email;
        this.ChucVu = ChucVu;
        this.SDT = SDT;
        this.HinhAnh = HinhAnh;
    }

    public Integer getMaND() {
        return MaND;
    }

    public void setMaND(Integer MaND) {
        this.MaND = MaND;
    }

    public String getTenND() {
        return TenND;
    }

    public void setTenND(String TenND) {
        this.TenND = TenND;
    }

    public String getTenTK() {
        return TenTK;
    }

    public void setTenTK(String TenTK) {
        this.TenTK = TenTK;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public int getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(int VaiTro) {
        this.VaiTro = VaiTro;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String HinhAnh) {
        this.HinhAnh = HinhAnh;
    }

   

    // Getters và Setters

    public Vector<?> toObjectArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}