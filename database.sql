CREATE DATABASE CLINIC_Nhom7_DA1;
GO
USE CLINIC_Nhom7_DA1;
GO

CREATE TABLE NGUOIDUNG (
    MaND VARCHAR(5) PRIMARY KEY NOT NULL,
    TenND NVARCHAR(50) NOT NULL,
    TenTK NVARCHAR(20) NOT NULL,
    MatKhau VARCHAR(20) NOT NULL,
    GioiTinh BIT NOT NULL,
    Email NVARCHAR(50) NOT NULL,
    VaiTro INT NOT NULL,
    ChucVu NVARCHAR(50) NOT NULL,
    SDT VARCHAR(10) NOT NULL,
    HinhAnh NVARCHAR(50) NOT NULL
);
GO

CREATE TABLE BENHNHAN (
    MaBN VARCHAR(5) PRIMARY KEY NOT NULL,
    TenBN NVARCHAR(50) NOT NULL,
    GioiTinh BIT NOT NULL,
    SoDT VARCHAR(10) NOT NULL,
    DiaChi NVARCHAR(50) NOT NULL,
    Email NVARCHAR(50) NOT NULL,
    TienSuBenh NVARCHAR(50) NOT NULL
);
GO

CREATE TABLE DONVITINH (
    MaDVT VARCHAR(5) PRIMARY KEY NOT NULL,
    TenDVT NVARCHAR(50) NOT NULL
);
GO

CREATE TABLE THUOC (
    MaThuoc VARCHAR(5) PRIMARY KEY NOT NULL,
    TenThuoc NVARCHAR(50) NOT NULL,
    CongDung NVARCHAR(200) NOT NULL,
    HDLieuLuong NVARCHAR(200) NOT NULL,
    GiaTien VARCHAR(100) NOT NULL,
    SoLuong INT NOT NULL,
    HanSD DATE NOT NULL,
    MaDVT VARCHAR(5) NOT NULL,
    CONSTRAINT FK_THUOC_DONVITINH FOREIGN KEY (MaDVT) REFERENCES DONVITINH(MaDVT)
);
GO

CREATE TABLE TOATHUOC (
    MaTT VARCHAR(5) PRIMARY KEY NOT NULL,
    MaBN VARCHAR(5) NOT NULL,
    TongTien VARCHAR(100) NOT NULL,
    NgayXuatHD DATE NOT NULL,
    MaND VARCHAR(5),
    CONSTRAINT FK_TOATHUOC_BENHNHAN FOREIGN KEY (MaBN) REFERENCES BENHNHAN(MaBN),
    CONSTRAINT FK_TOATHUOC_NGUOIDUNG FOREIGN KEY (MaND) REFERENCES NGUOIDUNG(MaND)
);
GO

CREATE TABLE TOATHUOCCHITIET (
    MaTTCT VARCHAR(5) PRIMARY KEY NOT NULL,
    MaTT VARCHAR(5) NOT NULL,
    MaBN VARCHAR(5) NOT NULL,
    MaND VARCHAR(5) NOT NULL,
    MaThuoc VARCHAR(5) NOT NULL,
    SoLuong INT NOT NULL,
    GiaBan VARCHAR(100) NOT NULL,
    ThanhTien VARCHAR(100),
    CONSTRAINT FK_TOATHUOCCHITIET_TOATHUOC FOREIGN KEY (MaTT) REFERENCES TOATHUOC(MaTT),
    CONSTRAINT FK_TOATHUOCCHITIET_THUOC FOREIGN KEY (MaThuoc) REFERENCES THUOC(MaThuoc)
);
GO

CREATE TABLE PHIEUKHAM (
    MaPK VARCHAR(5) PRIMARY KEY NOT NULL,
    MaBN VARCHAR(5) NOT NULL,
    MaND VARCHAR(5) NOT NULL,
    NgayKham DATE NOT NULL,
    MaBS VARCHAR(5) NOT NULL,
    ChuanDoan NVARCHAR(200) NOT NULL,
    DonThuoc NVARCHAR(200) NOT NULL,
    KhamToanThan NVARCHAR(200) NOT NULL,
    CONSTRAINT FK_PHIEUKHAM_BENHNHAN FOREIGN KEY (MaBN) REFERENCES BENHNHAN(MaBN),
    CONSTRAINT FK_PHIEUKHAM_NGUOIDUNG FOREIGN KEY (MaND) REFERENCES NGUOIDUNG(MaND)
);
GO
-- Bảng NGUOIDUNG
INSERT INTO NGUOIDUNG (MaND, TenND, TenTK, MatKhau, GioiTinh, Email, VaiTro, ChucVu, SDT, HinhAnh) VALUES
('ND001', N'BS CKII Trần Văn Kết', N'ketv', '123', 1, 'ket@gmail.com', 2, N'Bác sĩ', '0942465843', N'BS-KET.png'),
('ND002', N'THS BS Nguyễn Trọng Đức', N'duct', '123', 1, 'duc@gmail.com', 2, N'Bác sĩ', '0938131942', N'BS-DUC.png'),
('ND003', N'BS CKI Lưu Minh Phong', N'phonglm', '123', 1, 'phong@gmail.com', 2, N'Bác sĩ', '0365900495', N'BS-PHONG.png'),
('ND004', N'BS CKI Trần Việt Hùng', N'hungtv', '123', 1, 'hung@gmail.com', 2, N'Bác sĩ', '0947133664', N'BS-HUNG.png'),
('ND005', N'BS Nguyễn Văn Hành', N'hanhnv', '123', 1, 'hanh@gmail.com', 2, N'Bác sĩ', '0585716033', N'BS-HANH.png'),
('ND006', N'BS Trần Xuân Nhật Linh', N'linhtxn', '123', 1, 'linh@gmail.com', 2, N'Bác sĩ', '0374507299', N'BS-LINH.png'),
('ND007', N'BS Lê Viết Pháp', N'phaplv', '123', 1, 'phap@gmail.com', 2, N'Bác sĩ', '0939867435', N'BS-PHAP.png'),
('ND008', N'BS Thạch Sa Mết', N'metts', '123', 1, 'met@gmail.com', 2, N'Bác sĩ', '0399486325', N'BS-MET.png'),
('ND009', N'BS Lê Trường Sinh', N'sinhlt', '123', 1, 'sinh@gmail.com', 2, N'Bác sĩ', '0942456783', N'BS-SINH.png'),
('ND010', N'BS Trần Huỳnh Thanh Phương', N'phuongtht', '123', 0, 'phuong@gmail.com', 2, N'Bác sĩ', '0939568141', N'BS-PHUONG.png'),
('GD001', N'GĐ Vũ Chí Thành', N'thanhvc', '123', 1, 'thanh@gmail.com', 0, N'Giám đốc', '0365112357', N'GD-THANH.png'),
('NV001', N'Thái Tiểu Phụng', N'phungtt', '123', 0, 'phung@gmail.com', 1, N'Nhân viên', '0366040487', N'NV-TIEUPHUNG.png'),
('NV002', N'Huỳnh Văn Luận', N'luanhv', '123', 1, 'luan@gmail.com', 1, N'Nhân viên', '0942465843', N'NV-LUAN.png'),
('NV003', N'Huỳnh Văn Phúc', N'phuchv', '123', 1, 'phuc@gmail.com', 1, N'Nhân viên', '0373527383', N'NV-PHUC.png');
GO

-- Bảng BENHNHAN
INSERT INTO BENHNHAN (MaBN, TenBN, GioiTinh, SoDT, DiaChi, Email, TienSuBenh) VALUES
('BN001', N'Huỳnh Văn Phi', 1, '098311347', N'Cái Răng-Cần Thơ', 'phihv@gmail.com', N'Huyết áp thấp'),
('BN002', N'Thái Vân Anh', 0, '0932456772', N'Cái Răng-Cần Thơ', 'anhvt@gmail.com', N'Đau dạ dày'),
('BN003', N'Huỳnh Thiên Ân', 1, '0942365843', N'Ninh Kiều-Cần Thơ', 'anth@gmail.com', N'Dị ứng gà'),
('BN004', N'Thái Tràm My', 0, '0912321475', N'Ninh Kiều-Cần Thơ', 'mytt@gmail.com', N'Dị ứng cá rô'),
('BN005', N'Trần Minh Ánh', 0, '0985354622', N'Ninh Kiều-Cần Thơ', 'anhtm@gmail.com', N'Dị ứng tôm'),
('BN006', N'Nguyễn Đỗ Phước Lộc', 1, '0984646336', N'Ninh Kiều-Cần Thơ', 'locndp@gmail.com', N'Gan nhiễm mỡ'),
('BN007', N'Lê Huỳnh Như', 0, '067894535', N'Ninh Kiều-Cần Thơ', 'nhuhl@gmail.com', N'Loét dạ dạy'),
('BN008', N'Nguyễn Thị Phương Oanh', 0, '0987624529', N'Ninh Kiều-Cần Thơ', 'oanhntp@gmail.com', N'Dị ứng bò'),
('BN009', N'Nguyễn Thanh', 1, '0854967431', N'Ninh Kiều-Cần Thơ', 'thanhn@gmail.com', N'Dị ứng hàu'),
('BN010', N'Trần Gia Hân', 0, '0921345678', N'Ninh Kiều-Cần Thơ', 'hantg@gmail.com', N'Dị ứng cua'),
('BN011', N'Trần Khánh Băng', 1, '0921435465', N'Ninh Kiều-Cần Thơ', 'bangtk@gmail.com', N'Suy hô hấp'),
('BN012', N'Trần Quốc Sự', 1, '0987659432', N'Ninh Kiều-Cần Thơ', 'sutq@gmail.com', N'Dị ứng ốc'),
('BN013', N'Bùi Quang Thực', 1, '098746456', N'Ninh Kiều-Cần Thơ', 'thucbq@gmail.com', N'Dị ứng cá mè'),
('BN014', N'Trần Trung Hữu Phúc', 1, '0956845252', N'Ninh Kiều-Cần Thơ', 'phuctth@gmail.com', N'Dị ứng cà phê'),
('BN015', N'Nguyễn Vĩ Khiêm', 1, '0879644324', N'Ninh Kiều-Cần Thơ', 'khiemnv@gmail.com', N'Dị ứng thịt bịt');
GO

-- Bảng DONVITINH
INSERT INTO DONVITINH (MaDVT, TenDVT) VALUES
('V01', N'Viên'),
('H01', N'Hộp'),
('T01', N'Tuýt'),
('C01', N'Chai');
GO

-- Bảng THUOC
INSERT INTO THUOC (MaThuoc, TenThuoc, CongDung, HDLieuLuong, GiaTien, SoLuong, HanSD, MaDVT) VALUES
('TH001', N'DOXYCYCLINE 100MG', N'Tiêu diệt ký sinh trùng, chống viêm nhiễm hoặc ngăn chặn sự phát triển của các virus, nấm.', N'Sáng 1 viên, tối 1 viên sau ăn', '90.000', 20, '2024-01-01', 'H01'),
('TH002', N'MEDROL 16MG', N'Kháng viêm, dị ứng, bệnh rối loạn nội tiết', N'Sáng 1 viên, uống sau ăn', '120.000', 20, '2023-07-15', 'H01'),
('TH003', N'ANCARE', N'Kiểm soát lại hoạt động của hệ miễn dịch tăng cường hàng rào bảo vệ da, bổ sung men vi sinh', N'Sáng 1 viên, tối 1 viên sau ăn', '127.000', 20, '2024-06-23', 'H01'),
('TH004', N'KLENZIT C GEL 15G', N'Điều trị mụn trứng cá', N'Bôi buổi tối', '129.000', 10, '2024-07-18', 'T01'),
('TH005', N'GEL ACTIDERM DERIMA 40G', N'Giúp ngăn ngừa mụn và làm mờ vết thâm', N'Bôi buổi tối', '209.000', 10, '2024-07-19', 'T01'),
('TH006', N'CAFFINE 100MG', N'Điều trị chứng mất ngủ', N'Sáng 1 viên, tối 1 viên sau ăn', '85.000', 20, '2024-05-01', 'H01'),
('TH007', N'PARACETAMOL 500MG', N'Giảm đau, hạ sốt', N'Sáng 1 viên, tối 1 viên sau ăn', '70.000', 20, '2024-08-31', 'H01'),
('TH008', N'OMEZ 20MG', N'Điều trị trào ngược dạ dày thực quản', N'Sáng 1 viên trước bữa ăn sáng', '65.000', 20, '2024-04-21', 'H01'),
('TH009', N'ACICLOVIR 200MG', N'Tiêu diệt virus', N'Uống theo chỉ định của bác sĩ', '110.000', 20, '2023-12-01', 'H01'),
('TH010', N'VITAMIN C 500MG', N'Tăng cường hệ miễn dịch, chống oxi hóa', N'Sáng 1 viên sau bữa ăn', '40.000', 20, '2025-01-01', 'H01'),
('TH011', N'CALCIUM 500MG', N'Bổ sung canxi cho cơ thể', N'Sáng 1 viên, tối 1 viên sau ăn', '60.000', 20, '2025-06-15', 'H01'),
('TH012', N'VITAMIN D 1000IU', N'Tăng cường hấp thu canxi', N'Sáng 1 viên sau bữa ăn', '50.000', 20, '2025-12-31', 'H01');
GO

-- Bảng PHIEUKHAM
INSERT INTO PHIEUKHAM (MaPK, MaBN, MaND, NgayKham, MaBS, ChuanDoan, DonThuoc, KhamToanThan) VALUES
('PK001', 'BN001', 'ND001', '2024-08-01', 'ND001', N'Nhồi máu cơ tim', N'DOXYCYCLINE 100MG 1 viên x 7 ngày, MEDROL 16MG 1 viên x 5 ngày', N'Khám tim, kiểm tra huyết áp'),
('PK002', 'BN002', 'ND002', '2024-08-02', 'ND002', N'Viêm dạ dày', N'ANCARE 1 viên x 14 ngày, KLENZIT C GEL 15G bôi mỗi tối', N'Khám dạ dày, kiểm tra tiêu hóa'),
('PK003', 'BN003', 'ND003', '2024-08-03', 'ND003', N'Dị ứng thực phẩm', N'GEL ACTIDERM DERIMA 40G bôi mỗi tối', N'Khám da, kiểm tra dị ứng'),
('PK004', 'BN004', 'ND004', '2024-08-04', 'ND004', N'Viêm da', N'OMEZ 20MG 1 viên x 14 ngày', N'Khám da, kiểm tra hệ tiêu hóa'),
('PK005', 'BN005', 'ND005', '2024-08-05', 'ND005', N'Viêm khớp', N'PARACETAMOL 500MG 1 viên x 7 ngày', N'Khám khớp, kiểm tra huyết áp'),
('PK006', 'BN006', 'ND006', '2024-08-06', 'ND006', N'Gan nhiễm mỡ', N'CAFFINE 100MG 1 viên x 10 ngày', N'Khám gan, kiểm tra chỉ số mỡ'),
('PK007', 'BN007', 'ND007', '2024-08-07', 'ND007', N'Loét dạ dày', N'ACICLOVIR 200MG 1 viên x 7 ngày', N'Khám dạ dày, kiểm tra tiêu hóa'),
('PK008', 'BN008', 'ND008', '2024-08-08', 'ND008', N'Dị ứng thực phẩm', N'VITAMIN C 500MG 1 viên x 10 ngày', N'Khám da, kiểm tra dị ứng'),
('PK009', 'BN009', 'ND009', '2024-08-09', 'ND009', N'Dị ứng thực phẩm', N'VITAMIN D 1000IU 1 viên x 15 ngày', N'Khám da, kiểm tra dị ứng'),
('PK010', 'BN010', 'ND010', '2024-08-10', 'ND010', N'Dị ứng thực phẩm', N'CALCIUM 500MG 1 viên x 20 ngày', N'Khám da, kiểm tra dị ứng');
GO

INSERT INTO NGUOIDUNG (MaND, TenND, TenTK, MatKhau, GioiTinh, Email, VaiTro, ChucVu, SDT, HinhAnh) VALUES
('NV004', N'BS CKII Lý Tính Nhiệm', N'nhiemlt', '123', 1, 'lytinhnhiem@gmail.com', 2, N'Bác sĩ', '0787833283', N'BS-NHIEM.png')
