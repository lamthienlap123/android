package com.example.qlsv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity_Home extends AppCompatActivity {

    Connect cn = new Connect();
    ImageView imageuser;
    TextView name,mssv,malop;
    Button btnTTSV,btnTB,btnLH;
    Button btnKQHT,btnCTK,btnDMK;
    Button btnDX,btnDG,btnCN;
    String urlgetdata = cn.pChuoiHome_TTSV();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        show();
    }
    private void show() {
        imageuser = findViewById(R.id.icon_ttsv);
        name = findViewById(R.id.nameuser);
        mssv = findViewById(R.id.mssv);
        malop = findViewById(R.id.malop);
        getdata(urlgetdata);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.user);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);
        imageuser.setImageBitmap(circularBitmap);
        getTTSV();
        getLienHe();
        getDoiMatKhau();
        getDangXuat();
        getCongNo();
    }

    private void getdata(String url)
    {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try
                {
                    Intent intent = getIntent();
                    String msv = intent.getStringExtra("mssv");
                    JSONObject object = null;
                    for(int i=0; i < response.length(); i++)
                    {
                        object = response.getJSONObject(i);
                        String obsv = object.getString("MSSV");
                        if(msv.matches(obsv))
                        {
                            String text = object.getString("IMAGESV");
                            new LoadImageInternet().execute(text);
                            name.setText(object.getString("HOTEN"));
                            mssv.setText(object.getString("MSSV"));
                            malop.setText(object.getString("LOP"));
                            break;
                        }
                        if(i == response.length()-1)
                        {
                            Toast.makeText(MainActivity_Home.this, "Không tìm thấy thông tin sinh viên.", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_Home.this,"Lỗi!",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private class LoadImageInternet extends AsyncTask<String , Void, Bitmap>
    {
        Bitmap bitmapHinh;
        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url;
            try {
                url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmapHinh = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmapHinh;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageuser.setImageBitmap(bitmap);
        }
    }
    private void getCongNo() {
        Intent intent = getIntent();
        final String sv = intent.getStringExtra("mssv");
        final String pass = intent.getStringExtra("pass");
        btnCN = findViewById(R.id.btn_CN);
        btnCN.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Home.this, MainActivity_CongNo.class);
                intent.putExtra("mssv",sv);
                intent.putExtra("pass",pass);
                startActivity(intent);
            }
        });
    }
    private void getDangXuat() {
        btnDX = findViewById(R.id.btn_DX);
        btnDX.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Home.this, MainActivity_Login.class);
                startActivity(intent);
            }
        });
    }
    private void getDoiMatKhau() {
        Intent intent = getIntent();
        final int in = intent.getIntExtra("index",0);
        final String user = intent.getStringExtra("mssv");
        final String pass = intent.getStringExtra("pass");
        btnDMK = findViewById(R.id.btn_DMK);
        btnDMK.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Home.this, DoiMatKhau.class);
                intent.putExtra("mssv",user);
                intent.putExtra("pass",pass);
                intent.putExtra("index",in);
                startActivity(intent);
            }
        });
    }
    private void getLienHe() {
        btnLH = findViewById(R.id.btn_LH);
        btnLH.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Home.this, MainActivity_LichHoc.class);
                startActivity(intent);
            }
        });
    }
    private void getTTSV() {
        Intent intent = getIntent();
        final String pass = intent.getStringExtra("pass");
        final String sv = intent.getStringExtra("mssv");
        btnTTSV = findViewById(R.id.btn_TTSV);
        btnTTSV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Home.this, MainActivity_TTSV.class);
                intent.putExtra("pass",pass);
                intent.putExtra("mssv",sv);
                startActivity(intent);
            }
        });
    }
}
