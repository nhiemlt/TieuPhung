/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.plp_clinicmanage.utils;

import com.clinic.plp_clinicmanage.models.ThuocModel;
import com.clinic.plp_clinicmanage.models.ToaThuocChiTietModel;
import com.clinic.plp_clinicmanage.models.ToaThuocModel;
import com.clinic.plp_clinicmanage.services.BenhNhanDAO;
import com.clinic.plp_clinicmanage.services.ThuocDAO;
import com.clinic.plp_clinicmanage.ui.ToaThuoc;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;

public class PrintPrescription implements Printable {

    private List<ToaThuocChiTietModel> chiTietList;
    private ToaThuocModel toaThuoc;
    ThuocDAO thuocDAO;
    BenhNhanDAO bnDAO;
    XNumber xn = new XNumber();

    public PrintPrescription(ToaThuocModel toaThuoc, List<ToaThuocChiTietModel> chiTietList) {
        this.toaThuoc = toaThuoc;
        this.chiTietList = chiTietList;
        this.bnDAO = new BenhNhanDAO() {} ; 
        this.thuocDAO = new ThuocDAO(){}; 
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
            throws java.awt.print.PrinterException {

        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        // Bắt đầu vẽ nội dung đơn thuốc
        Font fontHeader = new Font("Arial", Font.BOLD, 16);
        Font fontNormal = new Font("Arial", Font.PLAIN, 12);

        int x = 50; // Điểm bắt đầu vẽ theo trục x
        int y = 50; // Điểm bắt đầu vẽ theo trục y

        // Vẽ thông tin cơ sở y tế
        g2d.setFont(fontHeader);
        g2d.drawString("PHÒNG KHÁM ĐA KHOA ENDLESS", x, y);
        y += 20;
        g2d.setFont(fontNormal);
        g2d.drawString("-Chăm sóc sức khỏe toàn diện-", x, y);
        y += 20;
        g2d.drawString("ĐC: Toà nhà FPT Polytechnic, Cần Thơ", x, y);
        y += 20;
        g2d.drawString("Email: Endless.clinic@gmail.com", x, y);

        // Vẽ thông tin đơn thuốc
        x = 50;
        y += 30;
        g2d.setFont(fontNormal);
        g2d.drawString("Mã Toa Thuốc: " + toaThuoc.getMaTT(), x, y);
        x += 150;
        g2d.drawString("Ngày Xuất: " + toaThuoc.getNgayXuatHD(), x, y);
        x += 150;
        g2d.drawString("Bệnh Nhân: " + bnDAO.selectById(toaThuoc.getMaBN()).getTenBN(), x, y);

        // Vẽ bảng thuốc
        x = 50;
        y += 30;
        g2d.drawLine(x, y, x + 500, y); // Đường ngang đầu bảng

        y += 20;
        g2d.drawString("SL", x, y);
        x += 30;
        g2d.drawString("Tên thuốc", x, y);
        x += 150;
        g2d.drawString("Giá bán", x, y);
        x += 130;
        g2d.drawString("Thành tiền", x, y);

        y += 20;
        g2d.drawLine(x - 310, y, x + 90, y); // Đường ngang phía dưới tiêu đề cột

        // Vẽ thuốc
        for (ToaThuocChiTietModel chiTiet : chiTietList) {
            ThuocModel thuoc = thuocDAO.selectById(chiTiet.getMaThuoc());
            x = 50;
            y += 20;
            g2d.drawString(Integer.toString(chiTiet.getSoLuong()), x, y); // số lượng
            x += 30;
            g2d.drawString(thuoc.getTenThuoc(), x, y); // tên thuốc
            x += 150;
            g2d.drawString(xn.formatDecimal(Float.parseFloat(chiTiet.getGiaBan() + "")), x, y); // giá bán
            x += 130;
            g2d.drawString(xn.formatDecimal(Integer.parseInt(chiTiet.getThanhTien() + "")), x, y); // Thành tiền
        }

        // Vẽ tổng số tiền
        x = 50;
        y += 30;
        g2d.drawLine(x, y, x + 500, y); // Đường ngang cuối bảng

        y += 20;
        Font fontTongTien = new Font("Arial", Font.BOLD, 14); // Chọn kích thước font mới
        g2d.setFont(fontTongTien);
        g2d.drawString("Tổng Tiền: " + xn.formatDecimal(Float.parseFloat(toaThuoc.getTongTien() + "")) + " VNĐ", x, y);

        // Vẽ chân trang
        y += 30;
        x += 120;
        g2d.drawString("Cảm ơn bạn đã tin tưởng dịch vụ của chúng tôi!", x, y);
        y += 20;
        x -= 80;
        g2d.drawString("Chúc bạn mau chóng hồi phục!", x, y);

        return PAGE_EXISTS;
    }

    public void printPrescription() {
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);

            if (job.printDialog()) {
                job.print();
            }
        } catch (PrinterException e) {
            e.printStackTrace();
            System.out.println("Lỗi in đơn thuốc");
        }
    }
}
