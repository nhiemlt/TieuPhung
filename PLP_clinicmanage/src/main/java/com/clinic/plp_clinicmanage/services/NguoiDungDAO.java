/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.services;

import com.clinic.plp_clinicmanage.models.NguoiDung;
import com.clinic.plp_clinicmanage.ui.NhanVien;
import com.clinic.plp_clinicmanage.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public abstract class NguoiDungDAO extends ClinicDAO<NguoiDung, String> {

    public void insert(NguoiDung model) {
        String sql = "INSERT INTO NGUOIDUNG(MaND, TenND, TenTK, MatKhau, GioiTinh,Email, VaiTro, ChucVu, SDT, HinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?)";
        XJdbc.update(sql,
                model.getMaND(),
                model.getTenND(),
                model.getTenTK(),
                model.getMatKhau(),
                model.isGioiTinh(),
                model.getEmail(),
                model.getVaiTro(),
                model.getChucVu(),
                model.getSDT(),
                model.getHinhAnh()
        );
    }
    public void role(NguoiDung model){
        String sql="SELECT * FROM NGUOIDUNG WHERE VAITRO=?";
        XJdbc.update(sql,
                model.getMaND(),
                model.getTenND(),
                model.getEmail(),
                model.getSDT()
        
                );
        
        
    }

    public void update(NguoiDung model) {
        String sql = "UPDATE NGUOIDUNG SET TenND=?, TENTK=?, MatKhau=?, GioiTinh=?,Email=?, VaiTro=?, ChucVu=?, SDT=?, HinhAnh=? WHERE MaND=?";
        XJdbc.update(sql,
                model.getTenND(),
                model.getTenTK(),
                model.getMatKhau(),
                model.isGioiTinh(),
                model.getEmail(),
                model.getVaiTro(),
                model.getChucVu(),
                model.getSDT(),
                model.getHinhAnh(),
                model.getMaND()
        );
    }

    public void delete(String MaND) {
        String sql = "DELETE FROM NGUOIDUNG WHERE MaND=?";
        XJdbc.update(sql, MaND);
    }

    public List<NguoiDung> selectAll() {
        String sql = "SELECT * FROM NguoiDung";
        return this.selectBySQL(sql);
    }

    public NguoiDung selectById(String maND) {
        String sql = "SELECT * FROM NguoiDung WHERE MaND=?";
        List<NguoiDung> list = this.selectBySQL(sql, maND);
        return list.size() > 0 ? list.get(0) : null;
    }

    public int selectVaiTro(String username, String pass) {
        String sql = "SELECT VaiTro FROM NGUOIDUNG WHERE TenTK = ? AND MatKhau = ?";
        List<NguoiDung> list = this.selectBySQL(sql, username, pass);
        if (list.size() > 0) {
            NguoiDung nd = list.get(0);
            return nd.getVaiTro();
        } else {
            return -1;
        }
    }

    public NguoiDung selectByUsername(String username) {
        String sql = "SELECT * FROM NguoiDung WHERE TenTK like ?";
        List<NguoiDung> list = this.selectBySQL(sql, username);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public NguoiDung selectByEmail(String email) {
        String sql = "SELECT * FROM NguoiDung WHERE Email like ?";
        List<NguoiDung> list = this.selectBySQL(sql, email);
        return list.size() > 0 ? list.get(0) : null;
    }

protected List<NguoiDung> selectBySQL(String sql, Object... args) {
    List<NguoiDung> list = new ArrayList<>();
    try (
        ResultSet rs = XJdbc.query(sql, args);
    ) {
        while (rs.next()) {
            NguoiDung entity = new NguoiDung();
            entity.setMaND(rs.getInt("MaND"));
            entity.setTenND(rs.getString("TenND")); // Corrected column name
            entity.setTenTK(rs.getString("TenTK")); // Corrected column name
            entity.setMatKhau(rs.getString("MatKhau")); // Corrected column name
            entity.setGioiTinh(rs.getBoolean("GioiTinh")); // Corrected column name
            entity.setEmail(rs.getString("Email")); // Corrected column name
            entity.setVaiTro(rs.getInt("VaiTro")); // Corrected column name
            entity.setChucVu(rs.getString("ChucVu")); // Corrected column name
            entity.setSDT(rs.getString("SDT")); // Corrected column name
            entity.setHinhAnh(rs.getString("HinhAnh")); // Corrected column name
            list.add(entity);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        throw new RuntimeException(ex);
    }
    return list;
}
}
