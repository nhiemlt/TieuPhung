/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.clinic.plp_clinicmanage.ui;

import com.clinic.plp_clinicmanage.models.BenhNhanModel;
import com.clinic.plp_clinicmanage.models.NguoiDung;
import com.clinic.plp_clinicmanage.models.ThuocModel;
import com.clinic.plp_clinicmanage.models.ToaThuocChiTietModel;
import com.clinic.plp_clinicmanage.models.ToaThuocModel;
import com.clinic.plp_clinicmanage.services.BenhNhanDAO;
import com.clinic.plp_clinicmanage.services.NguoiDungDAO;
import com.clinic.plp_clinicmanage.services.ThuocDAO;
import com.clinic.plp_clinicmanage.services.ToaThuocChiTietDAO;
import com.clinic.plp_clinicmanage.services.ToaThuocDAO;
import com.clinic.plp_clinicmanage.utils.Auth;
import com.clinic.plp_clinicmanage.utils.MsgBox;
import com.clinic.plp_clinicmanage.utils.PrintPrescription;
import com.clinic.plp_clinicmanage.utils.XNumber;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author DELL
 */
public class Pay extends javax.swing.JPanel {

    private Iterable<ToaThuocModel> toathuoc;
    ToaThuocDAO ttDao = new ToaThuocDAO() {
    };
    ThuocDAO tDao = new ThuocDAO() {
    };
    ToaThuocChiTietDAO ttctDao = new ToaThuocChiTietDAO() {
    };
    BenhNhanDAO bnDAO = new BenhNhanDAO() {
    };
    NguoiDungDAO ndDAO = new NguoiDungDAO() {
    };

    ArrayList<ToaThuocChiTietModel> gioHang;
    ToaThuocModel toaThuoc;
    XNumber xn = new XNumber();
    int row = -1;

    private class CircularButtonRenderer extends JButton implements TableCellRenderer {

        private ImageIcon icon;
        private int selectedRow = -1;

        public CircularButtonRenderer() {
            setOpaque(true);
            setBorderPainted(false);
            setContentAreaFilled(false);

            try {
                // Load the icon from the resources
                icon = new ImageIcon(getClass().getResource("/com.clinicmanage_icon/delete_1.png"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (row == selectedRow) {
                setBackground(Color.BLACK);
            } else {
                setBackground(table.getBackground());
            }

            setIcon(icon);
            setText((value == null) ? "" : value.toString());
            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }

    private class CircularButtonEditor extends DefaultCellEditor {

        private JButton button;

        public CircularButtonEditor(JTextField textField) {
            super(textField);

            button = new JButton();
            try {
                // Load the icon from the resources
                ImageIcon icon = new ImageIcon(getClass().getResource("/com.clinicmanage_icon/delete_1.png"));
                button.setIcon(icon);
            } catch (Exception e) {
                e.printStackTrace();
            }

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Point clickPoint = MouseInfo.getPointerInfo().getLocation();
                    SwingUtilities.convertPointFromScreen(clickPoint, tblGioHang);

                    int row = tblGioHang.rowAtPoint(clickPoint);

                    if (row >= 0 && row < tblGioHang.getRowCount()) {
                        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();

                        // Kết thúc sự kiện chỉnh sửa nếu đang có
                        if (tblGioHang.isEditing()) {
                            tblGioHang.getCellEditor().stopCellEditing();
                        }

                        model.removeRow(row);
                        tinhTien();
                    }
                }
            });

            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            button.setText((value == null) ? "" : value.toString());
            return button;
        }
    }

    public void tinhTien() {
        double tongTien = 0;
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            try {
                int a = Integer.parseInt(tblGioHang.getValueAt(i, 3) + "");
            } catch (Exception e) {
                tblGioHang.setValueAt(1, i, 3);
            }
            int soLuong = Integer.parseInt(tblGioHang.getValueAt(i, 3) + "");
            double gia = (xn.parseDecimal(tblGioHang.getValueAt(i, 2) + "")) * soLuong;
            tongTien += gia;
        }
        lblTongTien.setText(xn.formatDecimal(tongTien));
    }

    public Pay() {
        initComponents();
        fillTableLichSu();
        fillTablleThuoc();
        initForm();
    }

    public void initForm() {
        NguoiDung nd = Auth.user;
        txtTenBS.setText(nd.getTenND());
        txtChucVu.setText(nd.getChucVu());
        tblGioHang.getColumnModel().getColumn(4).setCellRenderer(new CircularButtonRenderer());
        tblGioHang.getColumnModel().getColumn(4).setCellEditor(new CircularButtonEditor(new JTextField()));
        tblGioHang.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(SwingConstants.LEFT);
            }
        });
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(49, 0, 158));
        headerRenderer.setForeground(Color.WHITE);

        for (int i = 0; i < tblGioHang.getColumnCount(); i++) {
            tblGioHang.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        tblGioHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point p = e.getPoint();
                row = tblGioHang.rowAtPoint(p);
                tblGioHang.repaint();
            }
        });
    }

    public void fillTableLichSu() {

        DefaultTableModel model = (DefaultTableModel) tblLichSu.getModel();
        model.setRowCount(0);
        try {
            List<ToaThuocModel> list = ttDao.selectAllDESC();
            for (ToaThuocModel cd : list) {
                Object[] row = {
                    cd.getMaTT(),
                    bnDAO.selectById(cd.getMaBN()).getTenBN(),
                    cd.getTongTien(),
                    cd.getNgayXuatHD()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void fillTablleThuoc() {
        String name = txtSearch.getText();
        DefaultTableModel model = (DefaultTableModel) tblThuoc.getModel();
        model.setRowCount(0);
        try {
            List<ThuocModel> list = tDao.selectByName(name);
            for (ThuocModel t : list) {
                Object[] row = {
                    t.getMaThuoc(),
                    t.getTenThuoc(),
                    t.getCongDung()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void setForm() {
        String keyword = txtKeyword.getText();
        BenhNhanModel bn = bnDAO.selectByEmail(keyword) == null ? bnDAO.selectBySDT(keyword) : bnDAO.selectByEmail(keyword);
        if (bn == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin bệnh nhân phù hợp!");
            txtKeyword.setText("");
            txtTenBN.setText("");
        } else {
            txtTenBN.setText(bn.getTenBN());
        }
    }

    public void updateTable() {
        String[] headers = new String[]{"MaTT", "MaBN", "TongTien", "NgayXuatHD"};
        DefaultTableModel model = new DefaultTableModel(headers, 0);

        for (ToaThuocModel item : this.toathuoc) {
            model.addRow(item.toObjectArray());
        }
        this.tblLichSu.setModel(model);
    }

    public void themGioHang(ThuocModel thuoc) {
        int size = tblGioHang.getRowCount();
        for (int i = 0; i < size; i++) {
            if (tblGioHang.getValueAt(i, 0).toString().equals(thuoc.getMaThuoc() + "")) {
                int sl = Integer.parseInt(tblGioHang.getValueAt(i, 3).toString());
                tblGioHang.setValueAt(sl + 1, i, 3);
                tinhTien();
                return;
            }
        }
        DefaultTableModel model = (DefaultTableModel) tblGioHang.getModel();
        Object[] row = {
            thuoc.getMaThuoc(),
            thuoc.getTenThuoc(),
            thuoc.getGiaTien(),
            1
        };
        model.addRow(row);
        tinhTien();
    }

    public boolean checkThanhToan() {
        if (txtTenBN.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin bệnh nhân");
            return false;
        }
        if (tblGioHang.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Chọn thuốc cần mua");
            return false;
        }
        return true;
    }

    public ToaThuocModel themToaThuoc() {
        String keyword = txtKeyword.getText();
        BenhNhanModel bn = bnDAO.selectByEmail(keyword) == null ? bnDAO.selectBySDT(keyword) : bnDAO.selectByEmail(keyword);
        ToaThuocModel tt = new ToaThuocModel();
        tt.setMaBN(bn.getMaBN());
        tt.setMaND(Auth.user.getMaND());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(new Date());
        tt.setNgayXuatHD(formattedDate);
        tt.setTongTien(xn.parseFloat(lblTongTien.getText()));
        try {
            ttDao.insert(tt);
            List<ToaThuocModel> allRecords = ttDao.selectAll();
            if (!allRecords.isEmpty()) {
                return allRecords.get(allRecords.size() - 1);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<ToaThuocChiTietModel> themChiTietToaThuoc(ToaThuocModel toaThuoc) {
        List<ToaThuocChiTietModel> ttctList = new ArrayList<>();
        for (int i = 0; i < tblGioHang.getRowCount(); i++) {
            int maBN = toaThuoc.getMaTT();
            int maND = toaThuoc.getMaBN();
            int maThuoc = Integer.parseInt(tblGioHang.getValueAt(i, 0).toString());
            int soLuong = Integer.parseInt(tblGioHang.getValueAt(i, 3).toString());
            float giaBan = Float.parseFloat(tblGioHang.getValueAt(i, 2).toString());
            float thanhTien = soLuong * giaBan;

            ToaThuocChiTietModel ttct = new ToaThuocChiTietModel();
            ThuocModel thuoc = tDao.selectById(maThuoc);
            if (thuoc.getSoLuong() < soLuong) {
                JOptionPane.showMessageDialog(this, "Thuốc " + thuoc.getTenThuoc() + "không còn đủ để bán, vui lòng chọn thuốc khác hoặc thay đổi số lượng!");
                tblGioHang.setValueAt(soLuong, i, 3);
                break;
            }
            ttct.setMaTT(toaThuoc.getMaTT());
            ttct.setMaBN(maBN);
            ttct.setMaND(maND);
            ttct.setMaThuoc(maThuoc);
            ttct.setSoLuong(soLuong);
            ttct.setGiaBan(giaBan);
            ttct.setThanhTien(thanhTien);

            ttctDao.insert(ttct);
            ttctList.add(ttct);
        }
        return ttctList;
    }

    public void thanhToan() {
        if (checkThanhToan()) {
            try {
                ToaThuocModel toaThuoc = themToaThuoc();
                if (toaThuoc == null) {
                    JOptionPane.showMessageDialog(this, "Thêm toa thuốc không thành công");
                } else {
                    List<ToaThuocChiTietModel> list = themChiTietToaThuoc(toaThuoc);
                    JOptionPane.showMessageDialog(this, "Thêm toa thuốc thành công!!"); 
                    fillTableLichSu();
                    PrintPrescription printBill = new PrintPrescription(toaThuoc, list);
                    printBill.printPrescription();
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Có lỗi sảy ra khi thêm toa thuốc");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jblTenBN = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenBN = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jblTenBS = new javax.swing.JLabel();
        txtTenBS = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtKeyword = new javax.swing.JTextField();
        jblSDT = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtChucVu = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnIn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLichSu = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblThuoc = new javax.swing.JTable();

        jLabel8.setText("jLabel8");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Xem trước"));
        jPanel1.setForeground(new java.awt.Color(0, 102, 102));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("PLP");

        jblTenBN.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jblTenBN.setText("Tên bệnh nhân:");

        jLabel5.setFont(new java.awt.Font("Open Sans ExtraBold", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("TOA THUỐC");

        txtTenBN.setEditable(false);
        txtTenBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenBNActionPerformed(evt);
            }
        });

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã", "Tên thuốc", "Giá bán", "SL", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        tblGioHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblGioHangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblGioHangKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);
        if (tblGioHang.getColumnModel().getColumnCount() > 0) {
            tblGioHang.getColumnModel().getColumn(0).setMaxWidth(30);
            tblGioHang.getColumnModel().getColumn(2).setMinWidth(100);
            tblGioHang.getColumnModel().getColumn(2).setMaxWidth(100);
            tblGioHang.getColumnModel().getColumn(3).setMinWidth(50);
            tblGioHang.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblGioHang.getColumnModel().getColumn(3).setMaxWidth(50);
            tblGioHang.getColumnModel().getColumn(4).setMaxWidth(20);
        }

        jblTenBS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jblTenBS.setText("Tên bác sĩ:");

        txtTenBS.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 102));
        jLabel4.setText("Phần mềm quản lí Phòng khám da liễu");

        jLabel1.setFont(new java.awt.Font("Open Sans ExtraBold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("Thành tiền:");

        jLabel6.setFont(new java.awt.Font("Open Sans ExtraBold", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("3.120.000");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 102, 102));
        jLabel7.setText("VND");

        txtKeyword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeywordActionPerformed(evt);
            }
        });

        jblSDT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jblSDT.setText("Sdt/ Email:");

        jLabel9.setText("Chức vụ bác sĩ:");

        txtChucVu.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Open Sans ExtraBold", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 102));
        jLabel10.setText("Tổng tiền:");

        lblTongTien.setFont(new java.awt.Font("Open Sans ExtraBold", 1, 24)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(0, 102, 102));
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongTien.setText("0");

        jLabel12.setFont(new java.awt.Font("Open Sans ExtraBold", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 102));
        jLabel12.setText("VND");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(29, 29, 29))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jblTenBN)
                                    .addComponent(jLabel9)
                                    .addComponent(jblTenBS, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jblSDT))
                                .addGap(9, 9, 9)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenBS, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtTenBN)
                                    .addComponent(txtKeyword)
                                    .addComponent(txtChucVu)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jblSDT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jblTenBN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTenBS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jblTenBS)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnIn.setText("In hóa đơn");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.clinicmanage_image/QLHĐ.jpg"))); // NOI18N

        jButton1.setText("Thêm thuốc");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.clinicmanage_icon/Search.png"))); // NOI18N
        jButton4.setText("Tìm kiếm toa thuốc");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lịch sử toa thuốc"));

        tblLichSu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã", "Tên bệnh nhân", "Tổng tiền", "Thời gian"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblLichSu);
        if (tblLichSu.getColumnModel().getColumnCount() > 0) {
            tblLichSu.getColumnModel().getColumn(0).setMinWidth(50);
            tblLichSu.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblLichSu.getColumnModel().getColumn(0).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com.clinicmanage_icon/Search.png"))); // NOI18N
        jButton2.setText("Tìm kiếm thuốc");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Gợi ý thuốc"));

        tblThuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã thuốc", "Tên thuốc", "Công dụng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblThuoc);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnIn))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextField1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2))
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(btnIn))
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenBNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenBNActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        fillTablleThuoc();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void txtKeywordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeywordActionPerformed
        setForm();
    }//GEN-LAST:event_txtKeywordActionPerformed

    private void tblThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocMouseClicked
        int selectedRow = tblThuoc.getSelectedRow();
        int id = Integer.parseInt(tblThuoc.getValueAt(selectedRow, 0).toString());
        ThuocModel thuoc = tDao.selectById(id);
        themGioHang(thuoc);
    }//GEN-LAST:event_tblThuocMouseClicked

    private void tblGioHangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblGioHangKeyPressed

    }//GEN-LAST:event_tblGioHangKeyPressed

    private void tblGioHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblGioHangKeyReleased
        tinhTien();
    }//GEN-LAST:event_tblGioHangKeyReleased

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked

        tinhTien();
    }//GEN-LAST:event_tblGioHangMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        thanhToan();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel jblSDT;
    private javax.swing.JLabel jblTenBN;
    private javax.swing.JLabel jblTenBS;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblLichSu;
    private javax.swing.JTable tblThuoc;
    private javax.swing.JTextField txtChucVu;
    private javax.swing.JTextField txtKeyword;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenBN;
    private javax.swing.JTextField txtTenBS;
    // End of variables declaration//GEN-END:variables
}
