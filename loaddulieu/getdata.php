<?php
	require "dbConnect.php";
	$query = "SELECT t.*, tennganh FROM ttsv t, nganh ng WHERE t.nganh = ng.manganh";
	$data = mysqli_query($connect,$query);
	class sinhvien
	{
		function sinhvien($mssv,$hoten,$gioitinh,$ngaysinh,$lop,$bacdaotao,$khoa,$tennganh,$diachi,$noisinh,$sodienthoai,
			$trangthai,$hinhsv)
		{
			$this->MSSV = $mssv;
			$this->HOTEN = $hoten;
			$this->GIOITINH = $gioitinh;
			$this->NGAYSINH = $ngaysinh;
			$this->LOP = $lop;
			$this->BACDAOTAO = $bacdaotao;
			$this->KHOA = $khoa;
			$this->NGANH = $tennganh;
			$this->DIACHI = $diachi;
			$this->NOISINH = $noisinh;
			$this->SODIENTHOAI = $sodienthoai;
			$this->TRANGTHAI = $trangthai;
			$this->IMAGESV = $hinhsv;
		}
	}
	$mang = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mang, new sinhvien($row['mssv'],$row['hoten'],$row['gioitinh'],$row['ngaysinh'],$row['lop'],$row['bacdaotao'],$row['khoa'],$row['tennganh'],$row['diachi'],$row['noisinh'],$row['sodienthoai'],$row['trangthai'],
			$row['hinhsv']));
	}
	echo json_encode($mang);
?>