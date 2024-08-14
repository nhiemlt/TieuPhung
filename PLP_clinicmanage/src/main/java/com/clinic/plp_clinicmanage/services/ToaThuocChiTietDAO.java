/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.services;


import com.clinic.plp_clinicmanage.models.ToaThuocChiTietModel;
import com.clinic.plp_clinicmanage.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */



public abstract class ToaThuocChiTietDAO extends ClinicDAO<ToaThuocChiTietModel, String>{
    public void insert(ToaThuocChiTietModel model) {
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

    public void update(ToaThuocChiTietModel model) {
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

     public List<ToaThuocChiTietModel> selectAll(){
        String sql="SELECT * FROM TOATHUOCCHITIET";
        return this.selectBySQL(sql);
    }
    
    public ToaThuocChiTietModel selectById(Integer mattct){
        String sql="SELECT * FROM TOATHUOCCHITIET WHERE MaTTCT=?";
        List<ToaThuocChiTietModel> list = this.selectBySQL(sql, mattct);
        return list.size() > 0 ? list.get(0) : null;
    }
   
    protected List<ToaThuocChiTietModel> selectBySQL(String sql, Object... args) {
        List<ToaThuocChiTietModel> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
//               (MaTTCT, MaTT, MaBN , MaND, MaThuoc, SoLuong, GiaBan, ThanhTien) 
                while (rs.next()) {
                    ToaThuocChiTietModel entity = new ToaThuocChiTietModel();
                    entity.setMaTTCT(rs.getInt("MaTTCT"));
                    entity.setMaTT(rs.getInt("MaTT"));
                    entity.setMaBN(rs.getInt("MaBN"));
                    entity.setMaND(rs.getInt("MaND"));
                    entity.setMaThuoc(rs.getInt("MaThuoc"));
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
