<?php
	require "dbConnect.php";

	$mssv = $_POST['MSSV'];
	$hinhsv = $_POST['HINHSV'];

	$query = "UPDATE ttsv SET hinhsv = '$hinhsv' WHERE mssv = '$mssv'";

	if(mysqli_query($connect,$query))
	{
		echo "success";
	}else
	{
		echo "error";
	}
?>