package com.example.qlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity_Login extends AppCompatActivity {

    Connect cn = new Connect();
    //String urlgetdata = "http://10.0.23.23:8080/loaddulieu/dangnhap.php";
    //String urlgetdata = "http://192.168.11.117:8080/loaddulieu/dangnhap.php";
    String urlgetdata = cn.pChuoiDN();
    EditText txtUser, txtPass;
    Button dangnhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        txtUser = findViewById(R.id.txtuser);
        txtPass = findViewById(R.id.txtpassword);
        login();
    }
    private void login() {
        dangnhap = findViewById(R.id.btn_login);
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedittext();
            }
        });
    }
    private void checkedittext()
    {
        if(txtUser.length()==0)
        {
            Toast.makeText(MainActivity_Login.this, "Yêu cầu nhập User!",Toast.LENGTH_SHORT).show();
        }
        else if(txtPass.length()==0) {
            Toast.makeText(MainActivity_Login.this, "Yêu cầu nhập Password!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ktralogin(urlgetdata);
        }
    }
    private void ktralogin(String url)
    {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try
                {
                    for (int i=0; i< response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);
                        final String user = object.getString("MSSV");
                        final String pass = object.getString("PASS");
                            if (user.equals(txtUser.getText().toString().trim()) && pass.equals(txtPass.getText().toString().trim())) {
                                Intent intent = new Intent(MainActivity_Login.this, MainActivity_Home.class);
                                intent.putExtra("mssv",user);
                                intent.putExtra("pass",pass);
                                startActivity(intent);
                                break;
                            }
                            if(i == response.length()-1)
                            {
                                Toast.makeText(MainActivity_Login.this, "Sai mat khau hoac tai khoan!", Toast.LENGTH_SHORT).show();
                            }
                        }
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_Login.this,"Lỗi!",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
