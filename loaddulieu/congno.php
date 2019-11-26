<?php
	require "dbConnect.php";
	$query = "SELECT * FROM congno";
	$data = mysqli_query($connect,$query);
	class congno
	{
		function congno($id, $mssv, $tenmh, $thanhtien, $danop, $congno)
		{
			$this->ID = $id;
			$this->MSSV = $mssv;
			$this->TENMH = $tenmh;
			$this->THANHTIEN = $thanhtien;
			$this->DANOP = $danop;
			$this->CONGNO = $congno;
		}
	}
	$mang = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mang, new congno($row['id'],$row['mssv'],$row['tenmh'],$row['thanhtien'],$row['danop'],$row['cno']));
	}
	echo json_encode($mang);
?>