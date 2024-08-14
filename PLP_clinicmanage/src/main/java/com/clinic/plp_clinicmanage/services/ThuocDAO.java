/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.services;

import com.clinic.plp_clinicmanage.models.ThuocModel;
import com.clinic.plp_clinicmanage.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public abstract class ThuocDAO extends ClinicDAO<ThuocModel, String> {

    public void insert(ThuocModel model) {
        String sql = "INSERT INTO THUOC(MaThuoc,TenThuoc,CongDung,HDLieuLuong,GiaTien,SoLuong,HanSD,MaDVT)values (?,?,?,?,?,?,?,?)";
        XJdbc.update(sql,
                model.getMaThuoc(),
                model.getTenThuoc(),
                model.getCongDung(),
                model.getHDLieuLuong(),
                model.getGiaTien(),
                model.getSoLuong(),
                model.getSoLuong(),
                model.getHanSD(),
                model.getMaDVT()
        );
    }

    public void update(ThuocModel model) {
        String sql = "UPDATE THUOC SET TenThuoc=?, CongDung=?, HDLieuLuong=?, GiaTien=?, SoLUong=?, HanSD=?, MaDVT=? WHERE MaThuoc=?";
        XJdbc.update(sql,
                model.getTenThuoc(),
                model.getCongDung(),
                model.getHDLieuLuong(),
                model.getGiaTien(),
                model.getSoLuong(),
                model.getHanSD(),
                model.getMaDVT(),
                model.getMaThuoc()
        );
    }

    public void delete(String MaThuoc) {
        String sql = "DELETE FROM THUOC WHERE MaThuoc=?";
        XJdbc.update(sql, MaThuoc);
    }

    @Override
    public List<ThuocModel> selectAll() {
        String sql = "SELECT * FROM THUOC";
        return this.selectBySQL(sql);
    }

    public ThuocModel selectById(String manv) {
        String sql = "SELECT * FROM Thuoc WHERE MaThuoc=?";
        List<ThuocModel> list = this.selectBySQL(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<ThuocModel> selectBySQL(String sql, Object... args) {
        List<ThuocModel> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
//             (MaThuoc,TenThuoc,CongDung,HDLieuLuong,GiaTien,SoLuong,HanSD,MaDVT)
                while (rs.next()) {
                    ThuocModel entity = new ThuocModel();
                    entity.setMaThuoc(rs.getString("MaThuoc"));
                    entity.setTenThuoc(rs.getString("TenThuoc"));
                    entity.setCongDung(rs.getString("CongDung"));
                    entity.setHDLieuLuong(rs.getString("HDLieuLuong"));
                    entity.setGiaTien(rs.getString("GiaTien"));
                    entity.setSoLuong(rs.getInt("SoLuong"));
                    entity.setHanSD(rs.getDate("HanSD"));
                    entity.setMaDVT(rs.getString("MaDVT"));
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

    private static class THUOC {

        public THUOC() {
        }
    }
}
