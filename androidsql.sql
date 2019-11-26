-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 26, 2019 lúc 06:55 PM
-- Phiên bản máy phục vụ: 10.4.8-MariaDB
-- Phiên bản PHP: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `thongtinsv`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `congno`
--

CREATE TABLE `congno` (
  `id` int(11) NOT NULL,
  `mssv` int(10) NOT NULL,
  `tenmh` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `thanhtien` double NOT NULL,
  `danop` double NOT NULL,
  `cno` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `congno`
--

INSERT INTO `congno` (`id`, `mssv`, `tenmh`, `thanhtien`, `danop`, `cno`) VALUES
(1, 2001160264, 'Công nghệ phần mềm', 1335000, 1335000, 0),
(2, 2001160264, 'Truyền thông kỹ thuật số', 1335000, 0, 1335000),
(3, 2001160264, 'Lập trình mã nguồn mở', 1335000, 1335000, 0),
(4, 2001160264, 'Thực hành lập trình mã nguồn mở', 890000, 0, 890000),
(5, 2001160264, 'Lập trình window nâng cao', 1335000, 1335000, 0),
(6, 2001160264, 'Thực hành công nghệ web', 575000, 575000, 0),
(7, 2001160351, 'Công nghệ phần mềm', 1335000, 1335000, 0),
(8, 2001160351, 'Mạng máy tính', 1335000, 1335000, 0),
(9, 2001160351, 'Kỹ thuật lập trình', 1335000, 1335000, 0),
(10, 2001160351, 'Thực hành công nghệ web', 575000, 0, 575000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctkhung`
--

CREATE TABLE `ctkhung` (
  `mamh` varchar(10) NOT NULL,
  `tenmh` varchar(100) NOT NULL,
  `dvht` int(11) NOT NULL,
  `trangthai` varchar(20) NOT NULL,
  `loaihp` tinyint(1) NOT NULL,
  `hocky` int(11) NOT NULL,
  `mnganh` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `ctkhung`
--

INSERT INTO `ctkhung` (`mamh`, `tenmh`, `dvht`, `trangthai`, `loaihp`, `hocky`, `mnganh`) VALUES
('000094', 'Anh văn A1', 3, 'Đạt', 1, 2, 'CNPM'),
('001661', 'Giáo dục quốc phòng - an ninh 2', 3, 'Đạt', 1, 1, 'CNPM'),
('002400', 'Kỹ năng giao tiếp', 2, 'Đạt', 0, 2, 'CNPM'),
('002412', 'Kỹ năng học tập hiểu quả', 2, 'Đạt', 1, 2, 'CNPM'),
('003491', 'Những nguyên lý cơ bản của chủ nghĩa Mác-Lênin 1', 2, 'Đạt', 1, 1, 'CNPM'),
('003671', 'Pháp luật đại cương', 2, 'Đạt', 1, 1, 'CNPM'),
('006097', 'Tin học văn phòng', 3, 'Đạt', 1, 1, 'CNPM'),
('006144', 'Toán cao cấp A1', 3, 'Đạt', 1, 1, 'CNPM'),
('006464', 'Vật lý đại cương 1', 2, 'Đạt', 1, 1, 'CNPM');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dangnhap`
--

CREATE TABLE `dangnhap` (
  `mssv` int(11) NOT NULL,
  `password` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `dangnhap`
--

INSERT INTO `dangnhap` (`mssv`, `password`) VALUES
(2001160174, '123456'),
(2001160264, '123456'),
(2001160351, '123456');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nganh`
--

CREATE TABLE `nganh` (
  `manganh` varchar(10) NOT NULL,
  `tennganh` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nganh`
--

INSERT INTO `nganh` (`manganh`, `tennganh`) VALUES
('CNPM', 'Công Nghệ Phần Mềm'),
('TCKT', 'Tài Chính Kế Toán');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ttsv`
--

CREATE TABLE `ttsv` (
  `mssv` char(10) NOT NULL,
  `hoten` varchar(100) NOT NULL,
  `gioitinh` varchar(3) NOT NULL,
  `ngaysinh` date NOT NULL,
  `lop` varchar(8) NOT NULL,
  `bacdaotao` varchar(50) NOT NULL,
  `khoa` varchar(100) NOT NULL,
  `nganh` varchar(50) NOT NULL,
  `diachi` varchar(150) NOT NULL,
  `noisinh` varchar(100) NOT NULL,
  `sodienthoai` char(10) NOT NULL,
  `trangthai` varchar(30) NOT NULL,
  `hinhsv` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `ttsv`
--

INSERT INTO `ttsv` (`mssv`, `hoten`, `gioitinh`, `ngaysinh`, `lop`, `bacdaotao`, `khoa`, `nganh`, `diachi`, `noisinh`, `sodienthoai`, `trangthai`, `hinhsv`) VALUES
('2001160174', 'Huỳnh Hoài Bảo', 'Nam', '1998-07-22', '07DHTH1', 'Đại Học', 'Công Nghệ Thông Tin', 'Công Nghệ Phần Mềm', 'Thủ Đức', 'Cà Mau', '0919375274', 'Đang học', 'photos.google.com/share/AF1QipMedtbcbl1uOPCgtrVQ-Ti6pybCcU-psVzBPf7Cl65KfsdfCzO5DlQCdBHdtUMhSw/photo/AF1QipPLNeDmBp2Ghd5S3Ru7xbqrq4ByEev_E8DYu7XE?key=NUxiak5kTkVVRWJRVFFhaU16dUE1LVRvMk1sZ2xB'),
('2001160264', 'Lâm Thiên Lập', 'Nam', '1998-12-02', '07DHTH1', 'Đại học', 'Công Nghệ Thông Tin', 'CNPM', '24 ngã tư chuồn chó', 'Cà Mau', '0825674486', 'đã nghỉ', 'http://sinhvien.hufi.edu.vn/GetImage.aspx?MSSV=2001160264'),
('2001160351', 'Đỗ Hoàng Thiện', 'Nam', '1998-09-07', '07DHTH5', 'đại học', 'Công nghệ thông tin', 'CNPM', '27 lê trọng tấn', 'An Giang', '0903876240', 'đang học', 'http://sinhvien.hufi.edu.vn/GetImage.aspx?MSSV=2001160351');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `congno`
--
ALTER TABLE `congno`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `ctkhung`
--
ALTER TABLE `ctkhung`
  ADD PRIMARY KEY (`mamh`);

--
-- Chỉ mục cho bảng `dangnhap`
--
ALTER TABLE `dangnhap`
  ADD PRIMARY KEY (`mssv`);

--
-- Chỉ mục cho bảng `nganh`
--
ALTER TABLE `nganh`
  ADD PRIMARY KEY (`manganh`);

--
-- Chỉ mục cho bảng `ttsv`
--
ALTER TABLE `ttsv`
  ADD PRIMARY KEY (`mssv`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `congno`
--
ALTER TABLE `congno`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
