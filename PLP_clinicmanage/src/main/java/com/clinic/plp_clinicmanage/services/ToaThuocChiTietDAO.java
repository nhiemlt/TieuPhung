/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.services;


import com.clinic.plp_clinicmanage.models.ToaThuocChiTiet;
import com.clinic.plp_clinicmanage.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */



public abstract class ToaThuocChiTietDAO extends ClinicDAO<ToaThuocChiTiet, String>{
    public void insert(ToaThuocChiTiet model) {
        String sql = "INSERT INTO TOATHUOCCHITIET(MaTTCT, MaTT, MaBN , MaND, MaThuoc, SoLuong, GiaBan, ThanhTien) VALUES (?, ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getMaTTCT(),
                model.getMaTT(),
                model.getMaBN(),
                model.getMaND(),
                model.getMaThuoc(),
                model.getSoLuong(),
                model.getGiaBan(),
                model.getThanhTien()
        );
    }

    public void update(ToaThuocChiTiet model) {
        String sql = "UPDATE TOATHUOCCHITIET SET MaTT=?, MaBN=?, MaND=?, MaThuoc=?, SoLuong=?, GiaBan=?, ThanhTien=? WHERE MaTTCT=?";
        XJdbc.update(sql,
                model.getMaTT(),
                model.getMaBN(),
                model.getMaND(),
                model.getMaThuoc(),
                model.getSoLuong(),
                model.getGiaBan(),
                model.getThanhTien(),
                model.getMaTTCT()
        );
    }

    public void delete(String MaTTCT) {
        String sql = "DELETE FROM TOATHUOCCHITIET WHERE MaTTCT=?";
        XJdbc.update(sql, MaTTCT);
    }

     public List<ToaThuocChiTiet> selectAll(){
        String sql="SELECT * FROM TOATHUOCCHITIET";
        return this.selectBySQL(sql);
    }
    
    public ToaThuocChiTiet selectById(String mattct){
        String sql="SELECT * FROM TOATHUOCCHITIET WHERE MaTTCT=?";
        List<ToaThuocChiTiet> list = this.selectBySQL(sql, mattct);
        return list.size() > 0 ? list.get(0) : null;
    }
   
    protected List<ToaThuocChiTiet> selectBySQL(String sql, Object... args) {
        List<ToaThuocChiTiet> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
//               (MaTTCT, MaTT, MaBN , MaND, MaThuoc, SoLuong, GiaBan, ThanhTien) 
                while (rs.next()) {
                    ToaThuocChiTiet entity = new ToaThuocChiTiet();
                    entity.setMaTTCT(rs.getString("MaTTCT"));
                    entity.setMaTT(rs.getString("MaTT"));
                    entity.setMaBN(rs.getString("MaBN"));
                    entity.setMaND(rs.getString("MaND"));
                    entity.setMaThuoc(rs.getString("MaThuoc"));
                    entity.setSoLuong(rs.getInt("SoLuong"));
                    entity.setGiaBan(rs.getString("GiaBan"));
                    entity.setThanhTien(rs.getString("ThanhTien"));
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
