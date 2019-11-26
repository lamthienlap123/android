package com.example.qlsv;

public class Connect {
    public String pChuoiDN()
    {
        //String url = "http://10.0.23.23:8080/loaddulieu/dangnhap.php";
        String url = "http://192.168.11.117:8080/loaddulieu/dangnhap.php";
        return url;
    }
    public String pChuoiHome_TTSV()
    {
        //String url = "http://10.0.23.23:8080/loaddulieu/getdata.php";//IP thiên thần top trend
        String url = "http://192.168.11.117:8080/loaddulieu/getdata.php";//IP Nhà
        return  url;
    }
    public String pChuoiDMK()
    {
        //String url = "http://10.0.23.23:8080/loaddulieu/update.php";
        String url = "http://192.168.11.117:8080/loaddulieu/update.php";
        return url;
    }
    public String pChuoiCN()
    {
        //String url = "http://10.0.23.23:8080/loaddulieu/congno.php";
        String url = "http://192.168.11.117:8080/loaddulieu/congno.php";
        return url;
    }
}
