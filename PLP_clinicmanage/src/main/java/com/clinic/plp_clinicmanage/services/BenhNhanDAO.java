/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.services;

import com.clinic.plp_clinicmanage.models.BenhNhanModel;
import com.clinic.plp_clinicmanage.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public abstract class BenhNhanDAO extends ClinicDAO<BenhNhanModel, Integer> {

    public void insert(BenhNhanModel model) {
        String sql = "INSERT INTO BENHNHAN( TenBN, GioiTinh,SoDT,DiaChi,Email,TienSuBenh) VALUES ( ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                
                model.getTenBN(),
                model.isGioiTinh(),
                model.getSoDT(),
                model.getDiaChi(),
                model.getEmail(),
                model.getTienSuBenh()
        );
    }

    public void update(BenhNhanModel model) {
        String sql = "UPDATE BENHNHAN SET TENBN=?, SODT=?, DiaChi=?, Email=?, TienSuBenh=? WHERE MaBN=?";
        XJdbc.update(sql,
                model.getTenBN(),
                model.getSoDT(),
                model.getDiaChi(),
                model.getEmail(),
                model.getTienSuBenh(),
                model.getMaBN()
        );
    }

     public BenhNhanModel selectByEmail(String email) {
        String sql = "SELECT * FROM BenhNhan WHERE Email like ?";
        List<BenhNhanModel> list = this.selectBySQL(sql, email);
        return list.size() > 0 ? list.get(0) : null;
    }
     
      public BenhNhanModel selectBySDT(String sdt) {
        String sql = "SELECT * FROM BenhNhan WHERE SoDT like ?";
        List<BenhNhanModel> list = this.selectBySQL(sql, sdt);
        return list.size() > 0 ? list.get(0) : null;
    }
    public void delete(Integer MaBN) {
        String sql = "DELETE FROM BENHNHAN WHERE MaBN=?";
        XJdbc.update(sql, MaBN);
    }

    public List<BenhNhanModel> selectAll() {
        String sql = "SELECT * FROM BENHNHAN";
        return this.selectBySQL(sql);
    }

    public List<BenhNhanModel> selectBySQL(String sql, Object... args) {
        List<BenhNhanModel> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    BenhNhanModel entity = new BenhNhanModel();
                    entity.setMaBN(rs.getInt("MaBN"));
                    entity.setTenBN(rs.getString("TENBN"));
                    entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                    entity.setSoDT(rs.getString("SoDT"));
                    entity.setDiaChi(rs.getString("DiaChi"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setTienSuBenh(rs.getString("TienSuBenh"));
                    list.add(entity);
                }
            } finally {
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }

    public com.clinic.plp_clinicmanage.models.BenhNhanModel selectById(Integer id) {
        String sql = "SELECT * FROM BenhNhan WHERE MaBN=?";

        List<BenhNhanModel> list = this.selectBySQL(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }
}
