<?php
	
	require "dbConnect.php";
	$query = "SELECT * FROM dangnhap";
	$data = mysqli_query($connect,$query);
	class dangnhap
	{
		function dangnhap($mssv, $password)
		{
			$this->MSSV = $mssv;
			$this->PASS = $password;
		}
	}
	$mang = array();
	while ($row = mysqli_fetch_assoc($data)) {
		array_push($mang, new dangnhap($row['mssv'],$row['password']));
	}
	echo json_encode($mang);
?>