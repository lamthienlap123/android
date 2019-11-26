package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DoiMatKhau extends AppCompatActivity {

    //String url = "http://192.168.11.117:8080/loaddulieu/update.php";
    Connect cn = new Connect();
    String url = cn.pChuoiDMK();
    EditText edtMatKhauCu,edtMKM,edtMKMR;
    Button btnCapNhat;
    ImageButton home;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doi_mat_khau);

        Intent intent = getIntent();
        String mssv = intent.getStringExtra("mssv");
        String pass = intent.getStringExtra("pass");

        AnhXa();
        id = mssv.trim();
        edtMatKhauCu.setText(pass.trim());
        action();
        getHome();
    }

    private void getHome() {
        Intent intent = getIntent();
        final String mssv = intent.getStringExtra("mssv");
        final String pass = intent.getStringExtra("pass");
        home = findViewById(R.id.imageButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoiMatKhau.this, MainActivity_Home.class);
                intent.putExtra("mssv",mssv);
                intent.putExtra("pass",pass);
                startActivity(intent);
            }
        });
    }

    private void action() {
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mkm = edtMKM.getText().toString().trim();
                String mkmr = edtMKMR.getText().toString().trim();
                if(mkm.matches("") || mkmr.equals(""))
                {
                    Toast.makeText(DoiMatKhau.this, "Vui lòng điền thông tin đầy đủ!", Toast.LENGTH_SHORT).show();
                }else {
                    if (!mkmr.matches(mkm)) {
                        Toast.makeText(DoiMatKhau.this, "Mật khẩu mới không trùng khớp vui lòng nhập lại!", Toast.LENGTH_SHORT).show();
                    } else {
                        capnhapMK(url);
                    }
                }
            }
        });
    }

    private void AnhXa() {
        btnCapNhat = findViewById(R.id.btn_capnhat);
        edtMatKhauCu = findViewById(R.id.editText1);
        edtMKM = findViewById(R.id.editText2);
        edtMKMR = findViewById(R.id.editText3);
    }
    private void capnhapMK(String url)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim().equals("success"))
                {
                    Toast.makeText(DoiMatKhau.this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                    
                }
                else 
                {
                    Toast.makeText(DoiMatKhau.this, "Lỗi cập nhật!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DoiMatKhau.this, "Xảy ra lỗi vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("MSSV",id);
                params.put("PASS",edtMKMR.getText().toString().trim());

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
