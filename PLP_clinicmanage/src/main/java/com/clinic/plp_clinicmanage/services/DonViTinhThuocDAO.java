/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.services;

import com.clinic.plp_clinicmanage.models.DonViTinhThuoc;
import com.clinic.plp_clinicmanage.models.NguoiDung;
import com.clinic.plp_clinicmanage.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public abstract class DonViTinhThuocDAO extends ClinicDAO<DonViTinhThuoc, String>{

    public void insert(DonViTinhThuoc model) {
        String sql = "INSERT INTO DONVITINH(MaDVT, TenDVT) VALUES (?, ?)";
        XJdbc.update(sql,
                model.getMaDVT(),
                model.getTenDVT()
        );
    }

    public void update(DonViTinhThuoc model) {
        String sql = "UPDATE DONVITINH SET TenDVT=? WHERE MaDVT=?";
        XJdbc.update(sql,
                model.getTenDVT(),
                model.getMaDVT()
        );
    }

    public void delete(String MaND) {
        String sql = "DELETE FROM DONVITINH WHERE MaDVT=?";
        XJdbc.update(sql, MaND);
    }

    public List<DonViTinhThuoc> selectAll() {
        String sql = "SELECT * FROM DONVITINH";
        return this.selectBySQL(sql);
    }

    public DonViTinhThuoc selectById(String maND) {
        String sql = "SELECT * FROM DONVITINH WHERE MaND=?";
        List<DonViTinhThuoc> list = this.selectBySQL(sql, maND);
        return list.size() > 0 ? list.get(0) : null;
    }

    public DonViTinhThuoc selectByUsername(String username) {
        String sql = "SELECT * FROM NguoiDung WHERE TenTK like ?";
        List<DonViTinhThuoc> list = this.selectBySQL(sql, username);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<DonViTinhThuoc> selectBySQL(String sql, Object... args) {
        List<DonViTinhThuoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while (rs.next()) {
                    DonViTinhThuoc entity = new DonViTinhThuoc();
                    entity.setMaDVT(rs.getString("MaDVT"));
                    entity.setTenDVT(rs.getString("TENDVT"));
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
}
