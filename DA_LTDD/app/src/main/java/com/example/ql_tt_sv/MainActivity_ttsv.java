package com.example.ql_tt_sv;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity_ttsv extends AppCompatActivity {


    ImageView imageuser;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttsv_main);
        showlstview();
        imageandname();
    }

    private void imageandname() {
        imageuser = (ImageView)findViewById(R.id.icon_ttsv);
        name = (TextView)findViewById(R.id.nameuser);
    }

    private void showlstview() {
        ListView lstTTSV = (ListView)findViewById(R.id.lstdstt);
        ArrayList<String> arrayTT;
        arrayTT = new ArrayList<String>();

        String[] arrstring = {"Trạng thái:","Giới Tính:","Ngày Sinh:"
                ,"MSSV:","Lớp:","Bậc Đào Tạo:","Khoa:","Chuyên Ngành","Địa Chỉ:","Số Điện Thoại:","Nơi Sinh:"};
        for (int i=0; i<arrstring.length;i++)
        {
            arrayTT.add(arrstring[i]);

        }
        ArrayAdapter ada = new ArrayAdapter(MainActivity_ttsv.this, android.R.layout.simple_list_item_1, arrayTT);

        lstTTSV.setAdapter(ada);
    }


}
