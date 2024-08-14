CREATE DATABASE CLINIC_Nhom7_DA1;
GO
USE CLINIC_Nhom7_DA1;
GO

CREATE TABLE NGUOIDUNG (
    MaND INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
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
    MaBN INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    TenBN NVARCHAR(50) NOT NULL,
    GioiTinh BIT NOT NULL,
    SoDT VARCHAR(10) NOT NULL,
    DiaChi NVARCHAR(50) NOT NULL,
    Email NVARCHAR(50) NOT NULL,
    TienSuBenh NVARCHAR(50) NOT NULL
);
GO

CREATE TABLE DONVITINH (
    MaDVT INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    TenDVT NVARCHAR(50) NOT NULL
);
GO

CREATE TABLE THUOC (
    MaThuoc INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    TenThuoc NVARCHAR(50) NOT NULL,
    CongDung NVARCHAR(200) NOT NULL,
    HDLieuLuong NVARCHAR(200) NOT NULL,
    GiaTien VARCHAR(100) NOT NULL,
    SoLuong INT NOT NULL,
    HanSD DATE NOT NULL,
    MaDVT INT NOT NULL,
    CONSTRAINT FK_THUOC_DONVITINH FOREIGN KEY (MaDVT) REFERENCES DONVITINH(MaDVT)
);
GO

CREATE TABLE TOATHUOC (
    MaTT INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    MaBN INT NOT NULL,
    TongTien VARCHAR(100) NOT NULL,
    NgayXuatHD DATE NOT NULL,
    MaND INT,
    CONSTRAINT FK_TOATHUOC_BENHNHAN FOREIGN KEY (MaBN) REFERENCES BENHNHAN(MaBN),
    CONSTRAINT FK_TOATHUOC_NGUOIDUNG FOREIGN KEY (MaND) REFERENCES NGUOIDUNG(MaND)
);
GO

CREATE TABLE TOATHUOCCHITIET (
    MaTTCT INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    MaTT INT NOT NULL,
    MaBN INT NOT NULL,
    MaND INT NOT NULL,
    MaThuoc INT NOT NULL,
    SoLuong INT NOT NULL,
    GiaBan VARCHAR(100) NOT NULL,
    ThanhTien VARCHAR(100),
    CONSTRAINT FK_TOATHUOCCHITIET_TOATHUOC FOREIGN KEY (MaTT) REFERENCES TOATHUOC(MaTT),
    CONSTRAINT FK_TOATHUOCCHITIET_THUOC FOREIGN KEY (MaThuoc) REFERENCES THUOC(MaThuoc)
);
GO

CREATE TABLE PHIEUKHAM (
    MaPK INT PRIMARY KEY IDENTITY(1,1) NOT NULL,
    MaBN INT NOT NULL,
    MaND INT NOT NULL,
    NgayKham DATE NOT NULL,
    MaBS INT NOT NULL,
    ChuanDoan NVARCHAR(200) NOT NULL,
    DonThuoc NVARCHAR(200) NOT NULL,
    KhamToanThan NVARCHAR(200) NOT NULL,
    CONSTRAINT FK_PHIEUKHAM_BENHNHAN FOREIGN KEY (MaBN) REFERENCES BENHNHAN(MaBN),
    CONSTRAINT FK_PHIEUKHAM_NGUOIDUNG FOREIGN KEY (MaND) REFERENCES NGUOIDUNG(MaND)
);
GO

-- Bảng NGUOIDUNG
INSERT INTO NGUOIDUNG (TenND, TenTK, MatKhau, GioiTinh, Email, VaiTro, ChucVu, SDT, HinhAnh) VALUES
(N'BS CKII Trần Văn Kết', N'ketv', '123', 1, 'ket@gmail.com', 2, N'Bác sĩ', '0942465843', N'BS-KET.png'),
(N'THS BS Nguyễn Trọng Đức', N'duct', '123', 1, 'duc@gmail.com', 2, N'Bác sĩ', '0938131942', N'BS-DUC.png'),
(N'BS CKI Lưu Minh Phong', N'phonglm', '123', 1, 'phong@gmail.com', 2, N'Bác sĩ', '0365900495', N'BS-PHONG.png'),
(N'BS CKI Trần Việt Hùng', N'hungtv', '123', 1, 'hung@gmail.com', 2, N'Bác sĩ', '0947133664', N'BS-HUNG.png'),
(N'BS Nguyễn Văn Hành', N'hanhnv', '123', 1, 'hanh@gmail.com', 2, N'Bác sĩ', '0585716033', N'BS-HANH.png'),
(N'BS Trần Xuân Nhật Linh', N'linhtxn', '123', 1, 'linh@gmail.com', 2, N'Bác sĩ', '0374507299', N'BS-LINH.png'),
(N'BS Lê Viết Pháp', N'phaplv', '123', 1, 'phap@gmail.com', 2, N'Bác sĩ', '0939867435', N'BS-PHAP.png'),
(N'BS Thạch Sa Mết', N'metts', '123', 1, 'met@gmail.com', 2, N'Bác sĩ', '0399486325', N'BS-MET.png'),
(N'BS Lê Trường Sinh', N'sinhlt', '123', 1, 'sinh@gmail.com', 2, N'Bác sĩ', '0942456783', N'BS-SINH.png'),
(N'BS Trần Huỳnh Thanh Phương', N'phuongtht', '123', 0, 'phuong@gmail.com', 2, N'Bác sĩ', '0939568141', N'BS-PHUONG.png'),
(N'GĐ Vũ Chí Thành', N'thanhvc', '123', 1, 'thanh@gmail.com', 0, N'Giám đốc', '0365112357', N'GD-THANH.png'),
(N'Thái Tiểu Phụng', N'phungtt', '123', 0, 'phungttpc08590@gmail.com', 1, N'Nhân viên', '0366040487', N'NV-TIEUPHUNG.png'),
(N'Huỳnh Văn Luận', N'luanhv', '123', 1, 'huynhhluan2004hg@gmail.com', 1, N'Nhân viên', '0942465843', N'NV-LUAN.png'),
(N'Huỳnh Văn Phúc', N'phuchv', '123', 1, 'phuchvpc08699@gmail.com', 1, N'Nhân viên', '0373527383', N'NV-PHUC.png'),
(N'BS CKII Lý Tính Nhiệm', N'nhiemlt', '123', 1, 'lytinhnhiem@gmail.com', 2, N'Bác sĩ', '0787833283', N'BS-NHIEM.png');
GO

-- Bảng BENHNHAN
INSERT INTO BENHNHAN (TenBN, GioiTinh, SoDT, DiaChi, Email, TienSuBenh) VALUES
(N'Huỳnh Văn Phi', 1, '098311347', N'Cái Răng-Cần Thơ', 'phihv@gmail.com', N'Huyết áp thấp'),
(N'Thái Vân Anh', 0, '0932456772', N'Cái Răng-Cần Thơ', 'anhvt@gmail.com', N'Đau dạ dày'),
(N'Huỳnh Thiên Ân', 1, '0942365843', N'Ninh Kiều-Cần Thơ', 'anth@gmail.com', N'Dị ứng gà'),
(N'Thái Tràm My', 0, '0912321475', N'Ninh Kiều-Cần Thơ', 'mytt@gmail.com', N'Dị ứng cá rô'),
(N'Trần Minh Ánh', 0, '0985354622', N'Ninh Kiều-Cần Thơ', 'anhtm@gmail.com', N'Dị ứng tôm'),
(N'Nguyễn Đỗ Phước Lộc', 1, '0984646336', N'Ninh Kiều-Cần Thơ', 'locndp@gmail.com', N'Gan nhiễm mỡ'),
(N'Lê Huỳnh Như', 0, '067894535', N'Ninh Kiều-Cần Thơ', 'nhuhl@gmail.com', N'Loét dạ dày'),
(N'Nguyễn Thị Phương Oanh', 0, '0987624529', N'Ninh Kiều-Cần Thơ', 'oanhntp@gmail.com', N'Dị ứng bò'),
(N'Nguyễn Thanh', 1, '0854967431', N'Ninh Kiều-Cần Thơ', 'thanhn@gmail.com', N'Dị ứng tôm'),
(N'Trần Nguyễn Xuân Thảo', 0, '0987131467', N'Cái Răng-Cần Thơ', 'thaotnx@gmail.com', N'Huyết áp cao');
GO

-- Bảng DONVITINH
INSERT INTO DONVITINH (TenDVT) VALUES
(N'Viên'),
(N'Hộp'),
(N'Thùng'),
(N'Chai');
GO

-- Bảng THUOC
INSERT INTO THUOC (TenThuoc, CongDung, HDLieuLuong, GiaTien, SoLuong, HanSD, MaDVT) VALUES
(N'Vitamin B12', N'Tốt cho não và trí nhớ', N'Uống ngày 2 viên', '120000', 1000, '2024-12-31', 1),
(N'Panadol', N'Hạ sốt', N'Uống 1 viên/ngày', '30000', 500, '2024-12-31', 2),
(N'Than hoạt tính', N'Tiêu độc', N'Uống 2 viên/ngày', '20000', 300, '2024-12-31', 3),
(N'Nước muối sinh lý', N'Rửa mắt', N'Sử dụng 2 lần/ngày', '10000', 200, '2024-12-31', 4),
(N'Amoxilin', N'Tiêu viêm', N'Uống 3 viên/ngày', '40000', 800, '2024-12-31', 1),
(N'Zinnat', N'Tiêu viêm', N'Uống 3 viên/ngày', '50000', 600, '2024-12-31', 1),
(N'Muối sinh lý', N'Rửa vết thương', N'Sử dụng 2 lần/ngày', '15000', 250, '2024-12-31', 4),
(N'Cefuroxim', N'Tiêu viêm', N'Uống 3 viên/ngày', '70000', 700, '2024-12-31', 1),
(N'Kefcin', N'Tiêu viêm', N'Uống 3 viên/ngày', '60000', 500, '2024-12-31', 1),
(N'Berocca', N'Tăng cường sức đề kháng', N'Uống 2 viên/ngày', '90000', 900, '2024-12-31', 1);
GO

-- Bảng TOATHUOC
INSERT INTO TOATHUOC (MaBN, TongTien, NgayXuatHD, MaND) VALUES
(1, '200000', '2024-07-14', 1),
(2, '300000', '2024-07-15', 2),
(3, '400000', '2024-07-16', 3),
(4, '500000', '2024-07-17', 4),
(5, '600000', '2024-07-18', 5);
GO

-- Bảng TOATHUOCCHITIET
INSERT INTO TOATHUOCCHITIET (MaTT, MaBN, MaND, MaThuoc, SoLuong, GiaBan, ThanhTien) VALUES
(1, 1, 1, 1, 10, '12000', '120000'),
(2, 2, 2, 2, 5, '60000', '300000'),
(3, 3, 3, 3, 8, '10000', '80000'),
(4, 4, 4, 4, 2, '5000', '10000'),
(5, 5, 5, 5, 4, '15000', '60000');
GO

-- Bảng PHIEUKHAM
INSERT INTO PHIEUKHAM (MaBN, MaND, NgayKham, MaBS, ChuanDoan, DonThuoc, KhamToanThan) VALUES
(1, 1, '2024-07-14', 1, N'Bệnh viêm phổi', N'Amoxilin', N'Kham tim mạch'),
(2, 2, '2024-07-15', 2, N'Bệnh đau dạ dày', N'Panadol', N'Kham tiêu hóa'),
(3, 3, '2024-07-16', 3, N'Bệnh viêm khớp', N'Kefcin', N'Kham xương khớp'),
(4, 4, '2024-07-17', 4, N'Bệnh viêm họng', N'Cefuroxim', N'Kham tai mũi họng'),
(5, 5, '2024-07-18', 5, N'Bệnh suy thận', N'Than hoạt tính', N'Kham tiết niệu');
GO
