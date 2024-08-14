/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.services;


import com.clinic.plp_clinicmanage.models.ToaThuoc;
import com.clinic.plp_clinicmanage.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */

public abstract class ToaThuocDAO extends ClinicDAO<ToaThuoc, String> {

    public void insert(ToaThuoc model) {
        String sql = "INSERT INTO TOATHUOC (MaTT, MaBN,	 TongTien, NgayXuatHD, MaND) VALUES (?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                model.getMaTT(),
                model.getMaBN(),
                model.getTongTien(),
                model.getNgayXuatHD(),
                model.getMaND()
        );
    }

    public void update(ToaThuoc model) {
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

     public List<ToaThuoc> selectAll(){
        String sql="SELECT * FROM ToaThuoc";
        return this.selectBySQL(sql);
    }
    
    public ToaThuoc selectById(String manv){
        String sql="SELECT * FROM ToaThuoc WHERE MaTT=?";
        List<ToaThuoc> list = this.selectBySQL(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }
  
    protected List<ToaThuoc> selectBySQL(String sql, Object... args) {
        List<ToaThuoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
//               (MaTT, MaBN,	 TongTien, NgayXuatHD, MaND) 
                while (rs.next()) {
                    ToaThuoc entity = new ToaThuoc();
                    entity.setMaTT(rs.getString("MaTT"));
                    entity.setMaBN(rs.getString("MaBN"));
                    entity.setTongTien(rs.getString("TongTien"));
                    entity.setNgayXuatHD(rs.getString("NgayXuatHD"));
                    entity.setMaND(rs.getString("MaND"));
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
