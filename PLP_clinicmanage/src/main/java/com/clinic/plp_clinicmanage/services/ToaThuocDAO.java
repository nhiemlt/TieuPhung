/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.services;


import com.clinic.plp_clinicmanage.models.ToaThuocModel;
import com.clinic.plp_clinicmanage.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */

public abstract class ToaThuocDAO extends ClinicDAO<ToaThuocModel, String> {

    public void insert(ToaThuocModel model) {
        String sql = "INSERT INTO TOATHUOC (MaTT, MaBN,	 TongTien, NgayXuatHD, MaND) VALUES (?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getMaTT(),
                model.getMaBN(),
                model.getTongTien(),
                model.getNgayXuatHD(),
                model.getMaND()
        );
    }

    public void update(ToaThuocModel model) {
        String sql = "UPDATE TOATHUOC SET  MaBN=?, TongTien=?, NgayXuatHD=?, MaND=? WHERE MaTT=?";
        XJdbc.update(sql,
                model.getMaBN(),
                model.getTongTien(),
                model.getNgayXuatHD(),
                model.getMaND(),
                model.getMaTT()
        );
    }

    public void delete(String MaTT) {
        String sql = "DELETE FROM TOATHUOC WHERE MaTT=?";
        XJdbc.update(sql, MaTT);
    }

     public List<ToaThuocModel> selectAll(){
        String sql="SELECT * FROM ToaThuoc";
        return this.selectBySQL(sql);
    }
    
    public ToaThuocModel selectById(Integer manv){
        String sql="SELECT * FROM ToaThuoc WHERE MaTT=?";
        List<ToaThuocModel> list = this.selectBySQL(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }
  
    protected List<ToaThuocModel> selectBySQL(String sql, Object... args) {
        List<ToaThuocModel> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
//               (MaTT, MaBN,	 TongTien, NgayXuatHD, MaND) 
                while (rs.next()) {
                    ToaThuocModel entity = new ToaThuocModel();
                    entity.setMaTT(rs.getInt("MaTT"));
                    entity.setMaBN(rs.getInt("MaBN"));
                    entity.setTongTien(rs.getString("TongTien"));
                    entity.setNgayXuatHD(rs.getString("NgayXuatHD"));
                    entity.setMaND(rs.getInt("MaND"));
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
