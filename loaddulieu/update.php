<?php
	require "dbConnect.php";

	$mssv = $_POST['MSSV'];
	$password = $_POST['PASS'];

	$query = "UPDATE dangnhap SET password = '$password' WHERE mssv = '$mssv'";

	if(mysqli_query($connect,$query))
	{
		echo "success";
	}else
	{
		echo "error";
	}
?>