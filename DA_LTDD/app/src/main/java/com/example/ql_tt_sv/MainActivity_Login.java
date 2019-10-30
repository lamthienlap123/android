package com.example.ql_tt_sv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity_Login extends AppCompatActivity {

    EditText edtuser,edtpassword;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        Anhxa();
        String hoten = "Huynh Hoai Bao";
        Log.d("HUYNHBAO",hoten);

    }

    private void Anhxa() {
        edtuser = (EditText)findViewById(R.id.edittextuser);
        edtpassword = (EditText)findViewById(R.id.edittextpassword);
        btn_login = (Button)findViewById(R.id.btn_login);

    }
}
