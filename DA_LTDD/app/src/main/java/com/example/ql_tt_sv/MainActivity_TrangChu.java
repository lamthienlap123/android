package com.example.ql_tt_sv;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_TrangChu extends AppCompatActivity {

    TextView txtuser;
    ImageView imageUser;
    Button btn_TTSV,btn_ThongBao,btn_KQHT,btn_LichHoc,btn_CTK,btn_CongNo,btn_DMK,btn_DangXuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trangchu);
        Anhxa();
    }

    private void Anhxa() {
        txtuser = (TextView)findViewById(R.id.nameuser);
        imageUser = (ImageView)findViewById(R.id.icon_sv);
        btn_TTSV = (Button)findViewById(R.id.btn_TTSV);
        btn_ThongBao = (Button)findViewById(R.id.btn_ThongBao);
        btn_KQHT = (Button)findViewById(R.id.btn_KQHT);
        btn_LichHoc = (Button)findViewById(R.id.btn_LichHoc);
        btn_CTK = (Button)findViewById(R.id.btn_CTKhung);
        btn_CongNo = (Button)findViewById(R.id.btn_CongNo);
        btn_DMK = (Button)findViewById(R.id.btn_DMK);
        btn_DangXuat = (Button)findViewById(R.id.btn_DangXuat);
    }
}
