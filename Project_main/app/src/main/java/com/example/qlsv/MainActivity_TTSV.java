package com.example.qlsv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.ArrayList;

public class MainActivity_TTSV extends AppCompatActivity {

    Connect cn = new Connect();
    String urlgetdata = cn.pChuoiHome_TTSV();
    ArrayList<List_ttsv> listviewSV;
    TTSV_listviewAdapter ttsv_listviewAdapter;
    ListView lvsinhvien;
    TextView hoten;
    ImageButton btnttsv;
    ImageView imgsv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ttsv_main);
        showlstview();
        action();
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
            imgsv.setImageBitmap(bitmap);
        }
    }
    private void action() {
        Intent intent = getIntent();
        final String sv = intent.getStringExtra("mssv");
        final String pass = intent.getStringExtra("pass");
        btnttsv = findViewById(R.id.btn_Home);
        btnttsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_TTSV.this, MainActivity_Home.class);
                intent.putExtra("pass",pass);
                intent.putExtra("mssv",sv);
                startActivity(intent);
            }
        });
    }

    private void showlstview() {
        imgsv = findViewById(R.id.img_ttsv);
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.user);
        Bitmap circularBitmap = ImageConverter.getRoundedCornerBitmap(bitmap, 100);
        imgsv.setImageBitmap(circularBitmap);
        lvsinhvien = (ListView) findViewById(R.id.lstdstt);
        listviewSV = new ArrayList<>();

        ttsv_listviewAdapter = new TTSV_listviewAdapter(listviewSV);
        lvsinhvien.setAdapter(ttsv_listviewAdapter);
        getdata(urlgetdata);

        lvsinhvien.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                List_ttsv ttsv = (List_ttsv) ttsv_listviewAdapter.getItem(position);
                Toast.makeText(MainActivity_TTSV.this,ttsv.nmtext, Toast.LENGTH_LONG).show();
            }
        });
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
                    JSONObject object=null;
                    for (int i=0; i < response.length(); i++)
                    {
                        object = response.getJSONObject(i);
                        String obsv = object.getString("MSSV");
                        if(msv.matches(obsv))
                        {
                            String imgtext =  object.getString("IMAGESV");
                            new LoadImageInternet().execute(imgtext);
                            hoten = (TextView) findViewById(R.id.nameuser);
                            hoten.setText(object.getString("HOTEN"));
                            listviewSV.add(new List_ttsv(1, "Trạng thái: ", object.getString("TRANGTHAI")));
                            listviewSV.add(new List_ttsv(2, "Giới tính: ", object.getString("GIOITINH")));
                            listviewSV.add(new List_ttsv(3,"Ngày Sinh: ", object.getString("NGAYSINH")));
                            listviewSV.add(new List_ttsv(4,"MSSV: ", object.getString("MSSV")));
                            listviewSV.add(new List_ttsv(5,"Lớp: ", object.getString("LOP")));
                            listviewSV.add(new List_ttsv(6,"Bậc Đào Tạo: ", object.getString("BACDAOTAO")));
                            listviewSV.add(new List_ttsv(7,"Khoa: ", object.getString("KHOA")));
                            listviewSV.add(new List_ttsv(8,"Chuyên Ngành: ", object.getString("NGANH")));
                            listviewSV.add(new List_ttsv(9,"Địa chỉ: ", object.getString("DIACHI")));
                            listviewSV.add(new List_ttsv(10,"Số điện thoại: ", object.getString("SODIENTHOAI")));
                            listviewSV.add(new List_ttsv(11,"Nơi sinh: ", object.getString("NOISINH")));
                            break;
                        }
                        if(i == response.length()-1)
                        {
                            Toast.makeText(MainActivity_TTSV.this, "Không tìm thấy thông tin sinh viên.", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ttsv_listviewAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_TTSV.this,"Lỗi!",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }

    public class List_ttsv{
        String lsttext;
        String nmtext;
        int listID;
        public List_ttsv(int listID, String lsttext, String nmtext){
            this.listID=listID;
            this.lsttext = lsttext;
            this.nmtext = nmtext;
        }
        public String getLsttext()
        {
            return lsttext;
        }
        public String getNmtext()
        {
            return nmtext;
        }
    }
    class TTSV_listviewAdapter extends BaseAdapter {
        final ArrayList<List_ttsv> listview;
        TTSV_listviewAdapter(ArrayList<List_ttsv> listview){this.listview = listview;}

        @Override
        public int getCount() {
            return listview.size();
        }
        @Override
        public Object getItem(int position){
            return listview.get(position);
        }

        @Override
        public long getItemId(int position) {
            return listview.get(position).listID;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View ViewTTSV;
            if(convertView == null){
                ViewTTSV =View.inflate(parent.getContext(),R.layout.ttsv_view,null);
            }else   ViewTTSV = convertView;
            //Bind dữ liệu phần tử vào View
            List_ttsv ttsv = (List_ttsv) getItem(position);
            ((TextView) ViewTTSV.findViewById(R.id.list_text)).setText(String.format("%s",ttsv.lsttext));
            ((TextView) ViewTTSV.findViewById(R.id.name_text)).setText(String.format("%s",ttsv.nmtext));
            return ViewTTSV;
        }
    }
}
