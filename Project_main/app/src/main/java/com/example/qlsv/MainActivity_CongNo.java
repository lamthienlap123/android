package com.example.qlsv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
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

import java.util.ArrayList;

public class MainActivity_CongNo extends AppCompatActivity {

    Connect cn = new Connect();
    String urlgetdata = cn.pChuoiCN();
    ArrayList<List_congno> listviewcn;
    CongNo_listviewAdapter congno_listviewAdapter;
    ListView listViewCN;
    ImageButton btncn;
    TextView txtcongno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congno_main);
        txtcongno = findViewById(R.id.tongcongno);
        showlstview();
        action();
    }
    private void action() {
        Intent intent = getIntent();
        final String mssv = intent.getStringExtra("mssv");
        final String pass = intent.getStringExtra("pass");
        btncn = findViewById(R.id.btn_cn_home);
        btncn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_CongNo.this, MainActivity_Home.class);
                intent.putExtra("mssv", mssv);
                intent.putExtra("pass",pass);
                startActivity(intent);
            }
        });
    }

    private void showlstview() {

        listviewcn = new ArrayList<>();
        getdata(urlgetdata);
        congno_listviewAdapter = new CongNo_listviewAdapter(listviewcn);

        listViewCN = (ListView)findViewById(R.id.lstcongno);
        listViewCN.setAdapter(congno_listviewAdapter);

        listViewCN.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                List_congno congno = (List_congno) congno_listviewAdapter.getItem(position);
                Toast.makeText(MainActivity_CongNo.this,congno.tenmh, Toast.LENGTH_LONG).show();
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
                    String mssv = intent.getStringExtra("mssv");
                    double tongcn=0;
                    for(int i = 0; i < response.length(); i++)
                    {
                        JSONObject object = response.getJSONObject(i);
                        String obsv = object.getString("MSSV");
                        if(obsv.equals(mssv))
                        {
                            int id = object.getInt("ID");
                            String tenmh = object.getString("TENMH");
                            double tt = object.getDouble("THANHTIEN");
                            double dn = object.getDouble("DANOP");
                            double cn = object.getDouble("CONGNO");
                            listviewcn.add(new List_congno(id, tenmh, tt, dn, cn));
                            tongcn += cn;
                        }
                    }
                    String s = String.valueOf(tongcn);
                    txtcongno.setText(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                congno_listviewAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity_CongNo.this,"Lỗi!",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }
    class List_congno{
        String tenmh;
        double thanhtien;
        double danop;
        double congno;
        int listID;
        public List_congno(int listID,String tenmh, double thanhtien,double danop, double congno){
            this.listID=listID;
            this.tenmh = tenmh;
            this.thanhtien = thanhtien;
            this.danop = danop;
            this.congno = congno;
        }
    }
    class CongNo_listviewAdapter extends BaseAdapter {
        final ArrayList<List_congno> listview;
        CongNo_listviewAdapter(ArrayList<List_congno> listview){this.listview = listview;}

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
                ViewTTSV =View.inflate(parent.getContext(),R.layout.congno_view,null);
            }else   ViewTTSV = convertView;
            //Bind dữ liệu phần tử vào View
            List_congno congno = (List_congno) getItem(position);
            ((TextView) ViewTTSV.findViewById(R.id.tenmh)).setText(String.format("%s",congno.tenmh));
            ((TextView) ViewTTSV.findViewById(R.id.thanhtien)).setText(String.format("%s",congno.thanhtien));
            ((TextView) ViewTTSV.findViewById(R.id.danop)).setText(String.format("%s",congno.danop));
            ((TextView) ViewTTSV.findViewById(R.id.congno)).setText(String.format("%s",congno.congno));
            return ViewTTSV;
        }
    }
}
